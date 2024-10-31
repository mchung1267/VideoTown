package com.mchung.authservice.service;

import com.mchung.authservice.dto.LoginRequestDto;
import com.mchung.authservice.dto.SignupRequestDto;
import com.mchung.authservice.entity.Member;
import com.mchung.authservice.utils.JwtUtil;
import com.mchung.authservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member signup(SignupRequestDto signupRequestDto) {
        try {
            if(memberRepository.findByEmail(signupRequestDto.getEmail()).isPresent()) {
                throw new IllegalArgumentException("이미 사용중인 이메일입니다");
            }

            String password = signupRequestDto.getPassword();
            if(!isPasswordValid(password)) {
                throw new IllegalArgumentException("정책에 어긋난 비밀번호입니다");
            }
            Member member = new Member();
            member.setEmail(signupRequestDto.getEmail());
            member.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));
            member.setName(signupRequestDto.getName());
            return storeAccount(member);

        }
        catch(DataAccessException e) {
            log.error("저장 중 오류 발생", e);
            throw new IllegalArgumentException("회원 저장에 실패했습니다");
        } catch(Exception e) {
            log.error("예상치 못한 오류 발생", e);
            throw new IllegalArgumentException("저장 중 예상치 못한 오류가 발생했습니다.");
        }
    }
    @Override
    public String signin(LoginRequestDto loginRequestDto) {
        try {
            Optional<Member> optionalMember = memberRepository.findByEmail(loginRequestDto.getEmail());
            if(optionalMember.isEmpty()) {
                throw new IllegalArgumentException("사용자가 존재하지 않습니다");
            }
            if(passwordEncoder.matches(loginRequestDto.getPassword(), optionalMember.get().getPassword())) {
                return jwtUtil.createToken(optionalMember.get().getId(), optionalMember.get().getEmail(), optionalMember.get().getName(), optionalMember.get().getType());
            } else {
                throw new IllegalArgumentException("비밀번호가 틀렸습니다");
            }
        } catch(DataAccessException e ) {
            log.error("데이터베이스 오류 발생");
            throw new IllegalArgumentException("데이터베이스 오류가 발생했습니다");
        } catch(Exception e) {
            log.error("오류 발생");
            throw new IllegalArgumentException("오류가 발생했습니다");
        }
    }
    private boolean isPasswordValid(String password) {
        String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*?_])[A-Za-z\\d!@#$%^&*?_]{8,16}$";
        return password.matches(passwordPattern);
    }
    private Member storeAccount(Member member) {
        try {
            return memberRepository.save(member);
        } catch(DataAccessException e) {
            log.error("저장 중 오류 발생", e);
            throw new IllegalArgumentException("회원 저장에 실패했습니다");
        } catch(Exception e) {
            log.error("예상치 못한 오류 발생", e);
            throw new IllegalArgumentException("저장 중 예상치 못한 오류가 발생했습니다.");
        }
    }

}
