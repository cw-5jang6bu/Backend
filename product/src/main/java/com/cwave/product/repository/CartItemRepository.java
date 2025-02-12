package com.cwave.product.repository;

import com.cwave.product.domain.entity.CartItemEntity;
import com.cwave.product.domain.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Integer> {

    List<CartItemEntity> findByCartId(Long cartId);
}
