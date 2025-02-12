package com.cwave.product.domain;

import com.cwave.product.domain.entity.CartEntity;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {

    private Long id;
    private Long memberId;
    private List<CartEntity> items;

}
