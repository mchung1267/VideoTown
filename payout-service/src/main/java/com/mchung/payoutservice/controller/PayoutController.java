package com.mchung.payoutservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payout-service")
@Slf4j
public class PayoutController {

    Environment env;

    public PayoutController(Environment env){
        this.env = env;
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome meow Service";
    }

    @GetMapping("/report")
    public String report(@RequestParam String term, @RequestParam String type){
        return "report";
    }
}
