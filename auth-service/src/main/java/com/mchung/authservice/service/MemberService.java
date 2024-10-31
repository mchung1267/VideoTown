package com.mchung.authservice.service;

import com.mchung.authservice.dto.LoginRequestDto;
import com.mchung.authservice.dto.SignupRequestDto;
import com.mchung.authservice.entity.Member;

public interface MemberService {
    Member signup(SignupRequestDto signupRequestDto);
    String signin(LoginRequestDto loginRequestDto);
}
