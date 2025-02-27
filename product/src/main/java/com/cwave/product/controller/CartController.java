package com.cwave.product.controller;

import com.cwave.product.domain.entity.CartItemEntity;
import com.cwave.product.domain.entity.OrderEntity;
import com.cwave.product.domain.entity.ProductEntity;
import com.cwave.product.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/{memberId}/products")
    public ResponseEntity<List<ProductEntity>> getProductsByMemberId(@PathVariable Long memberId) {
        List<ProductEntity> products = cartService.getAllCartItemListByMemberId(memberId);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }
}
