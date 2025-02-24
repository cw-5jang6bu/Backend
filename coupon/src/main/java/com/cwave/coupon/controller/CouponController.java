package com.cwave.coupon.controller;

import com.cwave.coupon.dto.CouponResponseDto;
import com.cwave.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
public class CouponController {

    Logger log = LoggerFactory.getLogger(CouponController.class);

    private final CouponService couponService;

    @GetMapping("/status")
    public CouponResponseDto checkCoupon(@RequestParam String memberId) {// ✅ email 쿼리 파라미터로 받기

        log.info("Check coupon data: " + memberId);

        return couponService.checkCouponStatus(memberId);
    }
}


