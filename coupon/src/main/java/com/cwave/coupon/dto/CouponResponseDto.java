package com.cwave.coupon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CouponResponseDto {
    private String memberId;
    private boolean issued;
    private String message;
    private Object timezone;
}

