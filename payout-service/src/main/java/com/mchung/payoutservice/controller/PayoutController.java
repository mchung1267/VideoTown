package com.mchung.payoutservice.controller;

import com.mchung.payoutservice.service.DailyRevenueService;
import com.mchung.payoutservice.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payout-service")
@Slf4j
public class PayoutController {

    private final DailyRevenueService dailyRevenueService;
    private final JwtUtil jwtUtil;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome meow Service";
    }

    @GetMapping("/report")
    public String report(@RequestParam String term, @RequestParam String type, @RequestHeader(name="Authorization")String token){
        String report = "";
        Claims claims = jwtUtil.getUserInfoFromToken(token.split(" ")[1].trim());
        Long userId = Long.valueOf((int)claims.get("id"));
        switch(term) {
            case "daily":
                report = dailyRevenueService.getDailyRevenueReport(userId, term);
        }
        return "report";
    }
}
