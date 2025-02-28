package com.cwave.member.controller;

import com.cwave.member.dto.AuthRequestDto;
import com.cwave.member.dto.AuthResponseDto;
import com.cwave.member.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto req) {
        log.info("로그인 요청: {}", req.getEmail());

        // 이메일 또는 비밀번호 누락 방지
        if (req.getEmail() == null || req.getPassword() == null) {
            log.warn("이메일 또는 비밀번호가 제공되지 않음.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new AuthResponseDto(false, "이메일과 비밀번호를 입력하세요.", null)); // ✅ 필드 순서 맞춤
        }

        AuthResponseDto response = authService.login(req);

        if (response == null || !response.isSuccess()) {
            log.warn("로그인 실패: {}", req.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponseDto(false, "로그인 실패. 이메일 또는 비밀번호를 확인하세요.", null)); // ✅ 필드 순서 맞춤
        }

        return ResponseEntity.ok(response);
    }
}

