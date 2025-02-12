package com.cwave.product.domain;

import lombok.Data;

@Data
public class OrderRequest {
    private Long memberId;
    private String address;
    private String name;
    private Integer totalAmount;
}
