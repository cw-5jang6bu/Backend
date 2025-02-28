package com.cwave.member.repository;

import com.cwave.member.entity.Coupon;
import com.cwave.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    @Query("SELECT c FROM Coupon c WHERE c.member = :member")  // ✅ 명확한 쿼리 작성
    Optional<Coupon> findByMember(@Param("member") Member member);
}

