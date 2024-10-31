package com.mchung.authservice.controller;

import com.mchung.authservice.dto.LoginRequestDto;
import com.mchung.authservice.dto.SignupRequestDto;
import com.mchung.authservice.entity.Member;
import com.mchung.authservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequestDto signupRequestDto) throws BadRequestException {
        Member member = memberService.signup(signupRequestDto);
        return "가입에 성공했습니다.";
    }

    @PostMapping("/signin")
    public String signin(@RequestBody LoginRequestDto loginRequestDto) throws BadRequestException{
        return memberService.signin(loginRequestDto);
    }


}
