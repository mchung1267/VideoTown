package com.mchung.authservice.controller;

import com.mchung.authservice.dto.LoginRequestDto;
import com.mchung.authservice.dto.SignupRequestDto;
import com.mchung.authservice.entity.Member;
import com.mchung.authservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/logout")
    public String logout() {
        return "Successfully Logged Out";
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequestDto signupRequestDto) throws BadRequestException {
        memberService.signup(signupRequestDto);
        return signupRequestDto.toString();
    }

    @PostMapping("/signin")
    public String signin(@RequestBody LoginRequestDto loginRequestDto) throws BadRequestException{
        return memberService.signin(loginRequestDto);
    }


}
