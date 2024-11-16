package com.mchung.payoutservice.controller;

import com.mchung.payoutservice.service.DailyRevenueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payout-service")
@Slf4j
public class PayoutController {

    private final DailyRevenueService dailyRevenueService;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome meow Service";
    }

    @GetMapping("/report")
    public String report(@RequestParam String term, @RequestParam String type){
        String report = "";
        switch(term) {
            case "daily":
                report = dailyRevenueService.getDailyRevenueReport(1L, term);
        }
        return "report";
    }
}
