package com.cwave.product.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class OrderItemDto {

    private Long itemId;
    private Integer count;
}
