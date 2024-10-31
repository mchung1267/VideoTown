package com.mchung.gatewayservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@Slf4j
public class JwtFilter extends AbstractGatewayFilterFactory<JwtFilter.Config> {

    @Value("${jwt.secret.key}")
    private String secret;

    public JwtFilter() {
        super(Config.class);
    }

    public GatewayFilter apply(Config config) {
        return(exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return chain.filter(exchange);
            }

            String authHeader = request.getHeaders().getOrEmpty(HttpHeaders.AUTHORIZATION).get(0);
            if(!authHeader.startsWith("Bearer ")) {
                return handleUnauthorized(response, "Invalid Authorization header format.");
            }

            String token = authHeader.substring(7);

            try {
                Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).getBody();
                ServerHttpRequest.Builder requestBuilder = request.mutate();
                for(Map.Entry<String, Object> entry : claims.entrySet()) {
                    String claimKey = "X-Claim-" + entry.getKey();
                    Object claimValue = String.valueOf(entry.getValue());
                    log.info("{}: {}", claimKey, claimValue);
                }

                request = requestBuilder.build();
                exchange = exchange.mutate().request(request).build();
            } catch (Exception e) {
                log.error("Validation Failed", e);
                return handleUnauthorized(response, "Invalid Authorization header format.");
            }
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("Custom POST filter: response status code -> {}", response.getStatusCode());
            }));
        };
    }

    private Mono<Void> handleUnauthorized(ServerHttpResponse response, String s) {
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json");
        String body = String.format("{\"message\":\"%s\"}", s);
        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes());
        return response.writeWith(Mono.just(buffer));
    }

    @Data
    public static class Config {
        private String preLogger;
        private String postLogger;
    }
}
