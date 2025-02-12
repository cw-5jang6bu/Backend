package com.cwave.product.domain;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    private Long id;
    private String name;
    private Integer stock;
    private Integer price;

}
