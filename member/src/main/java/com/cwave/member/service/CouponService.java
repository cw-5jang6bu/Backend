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

@Slf4j
@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;
    private final MemberRepository memberRepository;

    /**
     * 회원의 쿠폰 발급 여부 확인
     */
    public CouponResponseDto checkCouponIssued(String memberId) {
        Optional<Member> memberOptional = memberRepository.findById(Long.parseLong(memberId));

        if (memberOptional.isEmpty()) {
            return new CouponResponseDto(memberId, false, "회원 정보가 없습니다.", null);
        }

        // ✅ MySQL에서 쿠폰 조회
        Optional<Coupon> couponOptional = couponRepository.findByMember(memberOptional.get());

        if (couponOptional.isEmpty()) {
            return new CouponResponseDto(memberId, false, "쿠폰을 발급받은 적이 없습니다.", null);
        }

        return new CouponResponseDto(memberId, true, "쿠폰이 정상적으로 발급되었습니다.", null);
    }
}

