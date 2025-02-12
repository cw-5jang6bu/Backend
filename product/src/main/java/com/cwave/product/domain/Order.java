package com.cwave.product.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long id;
    private Long memberId;
    private String address;
    private String name;
    private LocalDateTime orderTime;
    private Integer totalAmount;
}
