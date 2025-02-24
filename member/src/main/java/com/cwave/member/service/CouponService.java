package com.cwave.member.service;

import com.cwave.coupon.dto.CouponResponseDto;
import com.cwave.coupon.entity.Coupon;
import com.cwave.coupon.entity.User;
import com.cwave.coupon.repository.CouponRepository;
import com.cwave.coupon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final UserRepository userRepository;
    private final CouponRepository couponRepository;
    private final RedisTemplate<String, Boolean> redisTemplate;

    private static final String COUPON_CACHE_PREFIX = "coupon:";

    public CouponResponseDto getCouponStatus(String userid) {
        String cacheKey = COUPON_CACHE_PREFIX + userid;

        // Redis cache에서 조회
        Boolean cacheCoupon = redisTemplate.opsForValue().get(cacheKey);
        if (cacheCoupon != null) {
            return new CouponResponseDto(userid, Boolean.parseBoolean(cacheCoupon.toString()));
        }

        // RDS 조회
        Optional<User> userOpt = userRepository.findByUserId(userid);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }

        Optional<Coupon> couponOpt = couponRepository.findByUser(userOpt.get());
        boolean issued = couponOpt.map(Coupon::isIssued).orElse(false);

        // Redis에 저장 ( 만료 시간 10분 )
        redisTemplate.opsForValue().set(cacheKey, issued, 10, TimeUnit.MINUTES);

        return new CouponResponseDto(userid, issued);
    }
}
