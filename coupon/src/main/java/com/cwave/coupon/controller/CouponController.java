package com.cwave.coupon.controller;

import com.cwave.coupon.dto.CouponResponseDto;
import com.cwave.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @GetMapping("/me")
    public CouponResponseDto checkCoupon(@RequestParam("userid") String userId) { // ✅ userId 쿼리 파라미터로 받기
        return couponService.getCouponStatus(userId);
    }
}


