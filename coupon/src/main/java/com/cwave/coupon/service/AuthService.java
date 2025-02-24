package com.cwave.coupon.service;

import com.cwave.coupon.dto.AuthRequestDto;
import com.cwave.coupon.dto.AuthResponseDto;
import com.cwave.coupon.entity.Member;
import com.cwave.coupon.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;

    public AuthResponseDto login(AuthRequestDto req) {
        Member member = memberRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new BadCredentialsException("사용자를 찾을 수 없습니다."));

        if (!req.getPassword().equals(member.getPassword())) {
            throw new BadCredentialsException("비밀번호가 올바르지 않습니다.");
        }

        // 로그인 성공하면 사용자 정보 반환
        return new AuthResponseDto("로그인 성공", member.getEmail());
    }
}


