package com.cwave.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDto {
    private boolean isSuccess;
    private String message;
    private String userId;

    public static AuthResponseDto success(String userId) {
        return new AuthResponseDto(true, "로그인 성공", userId);
    }

    public static AuthResponseDto failure(String message) {
        return new AuthResponseDto(false, message, null);
    }
}

