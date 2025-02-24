package com.cwave.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class CouponResponseDto {
    private String memberId;
    private boolean issued;
    private String message;
    private Object timezone;
}
