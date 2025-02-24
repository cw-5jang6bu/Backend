package com.cwave.coupon.service;

import com.cwave.coupon.dto.AuthRequestDto;
import com.cwave.coupon.dto.AuthResponseDto;
import com.cwave.coupon.entity.User;
import com.cwave.coupon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    public AuthResponseDto login(AuthRequestDto req) {
        User user = userRepository.findByUserId(req.getUserid())
                .orElseThrow(() -> new BadCredentialsException("사용자를 찾을 수 없습니다."));

        if (!req.getPassword().equals(user.getPassword())) {
            throw new BadCredentialsException("비밀번호가 올바르지 않습니다.");
        }

        // 로그인 성공하면 사용자 정보 반환
        return new AuthResponseDto("로그인 성공", user.getUserId());
    }
}


