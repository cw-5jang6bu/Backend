package com.cwave.product.domain;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    private Long id;
    private Long memberId;
    private String address;
    private String cardNumber;
    private String memberName;

}
