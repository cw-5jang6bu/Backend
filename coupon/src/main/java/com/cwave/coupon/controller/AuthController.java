package com.cwave.coupon.controller;

import com.cwave.coupon.dto.AuthRequestDto;
import com.cwave.coupon.dto.AuthResponseDto;
import com.cwave.coupon.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto req) {
        log.info("login auth request: {}", req.getUserid());
        return authService.login(req);
    }
}
