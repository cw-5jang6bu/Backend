package com.cwave.product.service.Impl;

import com.cwave.product.domain.entity.PaymentEntity;
import com.cwave.product.repository.OrderRepository;
import com.cwave.product.repository.PaymentRepository;
import com.cwave.product.service.PaymentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Override
    public PaymentEntity createPayment(Long memberId, String address, String cardNumber, String name) {

        PaymentEntity payment = PaymentEntity.builder()
                .memberId(memberId)
                .address(address)
                .cardNumber(cardNumber)
                .memberName(name)
                .build();

        return paymentRepository.save(payment);
    }

}
