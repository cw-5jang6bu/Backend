package com.cwave.coupon.service;

import com.cwave.coupon.dto.CouponResponseDto;
import com.cwave.coupon.entity.Coupon;
import com.cwave.coupon.entity.User;
import com.cwave.coupon.repository.CouponRepository;
import com.cwave.coupon.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CouponServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CouponRepository couponRepository;

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @Mock
    private ValueOperations<String, Object> valueOperations;

    @InjectMocks
    private CouponService couponService;

    private User user;
    private Coupon coupon;

    @BeforeEach
    void setUp() {
        user = new User(1L, "testUser", "password");
        coupon = new Coupon(1L, user, true);
    }

    @Test
    void 쿠폰_조회_성공_레디스_캐싱() {
        // given
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        when(valueOperations.get("coupon:testUser")).thenReturn("true");

        // when
        CouponResponseDto response = couponService.getCouponStatus("testUser");

        // then
        assertNotNull(response);
        assertTrue(response.isIssued());
    }

    @Test
    void 쿠폰_조회_실패_존재하지_않는_사용자() {
        // given
        when(userRepository.findByUserId("nonExistentUser")).thenReturn(Optional.empty());

        // when & then
        assertThrows(RuntimeException.class, () -> couponService.getCouponStatus("nonExistentUser"));
    }
}
