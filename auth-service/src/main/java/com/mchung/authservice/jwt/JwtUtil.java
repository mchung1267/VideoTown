package com.mchung.authservice.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j(topic = "JwtUtil")
@Component
public class JwtUtil {
    public static final String prefix = "Bearer ";
    private final long maxToken = 3600000L;

    @Value("${jwt.secret.key}")
    private String secretKey;

    private Key key;

    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    public String createToken(Long id, String email, String name, String type) {
        Date date = new Date();
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("email", email);
        claims.put("name", name);
        claims.put("type", type);

        return prefix +
                Jwts.builder()
                        .setSubject("" + id)
                        .setClaims(claims)
                        .setExpiration(new Date(date.getTime() + maxToken))
                        .signWith(signatureAlgorithm, key)
                        .compact();
    }
}
