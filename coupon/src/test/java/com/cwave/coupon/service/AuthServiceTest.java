package com.cwave.coupon.service;

import com.cwave.coupon.dto.AuthRequestDto;
import com.cwave.coupon.dto.AuthResponseDto;
import com.cwave.coupon.entity.User;
import com.cwave.coupon.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // ✅ Mockito 확장 사용
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "testUser", "password");
    }

    @Test
    void 로그인_성공() {
        // given
        AuthRequestDto requestDto = new AuthRequestDto("testUser", "password");

        when(userRepository.findByUserId("testUser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);

        // when
        AuthResponseDto response = authService.login(requestDto);

        // then
        assertNotNull(response);
        assertEquals("testUser", response.getUserId());
    }

    @Test
    void 로그인_실패_잘못된_비밀번호() {
        // given
        AuthRequestDto requestDto = new AuthRequestDto("testUser", "wrongPassword");

        when(userRepository.findByUserId("testUser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrongPassword", "encodedPassword")).thenReturn(false);

        // when & then
        assertThrows(RuntimeException.class, () -> authService.login(requestDto));
    }

    @Test
    void 로그인_실패_존재하지_않는_사용자() {
        // given
        AuthRequestDto requestDto = new AuthRequestDto("nonExistentUser", "password");

        when(userRepository.findByUserId("nonExistentUser")).thenReturn(Optional.empty());

        // when & then
        assertThrows(RuntimeException.class, () -> authService.login(requestDto));
    }
}
