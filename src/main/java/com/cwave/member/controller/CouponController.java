package com.cwave.member.controller;

import com.cwave.member.dto.CouponResponseDto;
import com.cwave.member.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cwave.member.dto.CouponResponseDto;
import com.cwave.member.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")  // ✅ React 개발 서버에서 호출 허용
@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
public class CouponController {

    private static final Logger log = LoggerFactory.getLogger(CouponController.class);
    private final CouponService couponService;

    @GetMapping("/me")
    public ResponseEntity<CouponResponseDto> checkCoupon(@RequestParam String email) {
        log.info("✅ 쿠폰 상태 조회 요청: {}", email);
        CouponResponseDto couponResponse = couponService.checkCouponIssued(email);
        return ResponseEntity.ok(couponResponse);
    }
}




