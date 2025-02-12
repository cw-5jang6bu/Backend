package com.cwave.product.domain;

import com.cwave.product.domain.entity.CartItemEntity;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    private Long id;
    private Long productId;
    private Integer quantity;
}
