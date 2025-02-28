package com.cwave.member.service;

import com.cwave.member.dto.AuthRequestDto;
import com.cwave.member.dto.AuthResponseDto;
import com.cwave.member.entity.Member;
import com.cwave.member.repository.MemberRepository;
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

        return AuthResponseDto.success(member.getEmail());
    }
}

