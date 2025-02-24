package com.cwave.coupon.repository;

import com.cwave.coupon.entity.Coupon;
import com.cwave.coupon.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByMember(Member member);
}
