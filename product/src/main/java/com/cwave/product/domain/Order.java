package com.cwave.product.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDto {

    private List<OrderItemDto> itemList;
    private Long memberId;
    private String name;
    private String address;
}
