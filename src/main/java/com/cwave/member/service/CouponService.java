package com.cwave.member.service;

import com.cwave.member.dto.CouponResponseDto;
import com.cwave.member.entity.Coupon;
import com.cwave.member.entity.Member;
import com.cwave.member.repository.CouponRepository;
import com.cwave.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.cwave.member.dto.CouponResponseDto;
import com.cwave.member.entity.Coupon;
import com.cwave.member.entity.Member;
import com.cwave.member.repository.CouponRepository;
import com.cwave.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.cwave.member.dto.CouponResponseDto;
import com.cwave.member.entity.Coupon;
import com.cwave.member.entity.Member;
import com.cwave.member.repository.CouponRepository;
import com.cwave.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;
    private final MemberRepository memberRepository;

    public CouponResponseDto checkCouponIssued(String email) {
        Optional<Member> memberOptional = memberRepository.findByEmail(email);

        if (memberOptional.isEmpty()) {
            return new CouponResponseDto(email, false, "❌ 회원 정보가 없습니다.");
        }

        Member member = memberOptional.get();
        Optional<Coupon> couponOptional = couponRepository.findByMember(member);

        if (couponOptional.isEmpty()) {
            return new CouponResponseDto(email, false, "❌ 쿠폰을 발급받은 적이 없습니다.");
        }

        Coupon coupon = couponOptional.get();
        System.out.println("✅ 쿠폰 발급 상태 확인: " + coupon.isIssued());

        return new CouponResponseDto(email, coupon.isIssued(), "✅ 쿠폰이 정상적으로 발급되었습니다.");
    }
}







