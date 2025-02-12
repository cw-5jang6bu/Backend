package com.cwave.product.domain;

import lombok.Data;

@Data
public class PaymentRequest {
    private Long memberId;
    private String address;
    private String cardNumber;
    private String name;

}
