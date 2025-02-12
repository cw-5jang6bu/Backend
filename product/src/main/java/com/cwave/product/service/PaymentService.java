package com.cwave.product.service;

import com.cwave.product.domain.entity.PaymentEntity;

public interface PaymentService {

    PaymentEntity createPayment(Long memberId, String address, String cardNumber, String name);
}
