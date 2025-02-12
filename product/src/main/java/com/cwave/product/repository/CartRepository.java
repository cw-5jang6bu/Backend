package com.cwave.product.repository;

import com.cwave.product.domain.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {

    CartEntity findByMemberId(Long memberId);
}
