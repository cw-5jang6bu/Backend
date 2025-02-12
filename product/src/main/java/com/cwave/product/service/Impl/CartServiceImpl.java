package com.cwave.product.service.Impl;

import com.cwave.product.domain.entity.CartEntity;
import com.cwave.product.domain.entity.CartItemEntity;
import com.cwave.product.domain.entity.ProductEntity;
import com.cwave.product.repository.CartItemRepository;
import com.cwave.product.repository.CartRepository;
import com.cwave.product.service.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;



    @Override
    public List<ProductEntity> getAllCartItemListByMemberId(@PathVariable("memberId") Long memberId) {
        CartEntity cart = cartRepository.findByMemberId(memberId);
        if (cart == null) {
            throw new IllegalArgumentException("Cart not found for memberId: " + memberId);
        }

        List<CartItemEntity> cartItems = cartItemRepository.findByCartId(cart.getId());

        // ✅ CartItem에서 ProductEntity 추출
        return cartItems.stream()
                .map(CartItemEntity::getProduct)  // CartItem에서 Product 가져오기
                .collect(Collectors.toList());
    }
}
