package com.cwave.product.controller;

import com.cwave.product.domain.PaymentRequest;
import com.cwave.product.domain.entity.PaymentEntity;
import com.cwave.product.repository.PaymentRepository;
import com.cwave.product.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentRepository paymentRepository;

    @PostMapping
    public ResponseEntity<PaymentEntity> createPayment(@RequestBody PaymentRequest paymentRequest) {
        PaymentEntity payment = paymentService.createPayment(
                paymentRequest.getMemberId(),
                paymentRequest.getAddress(),
                paymentRequest.getCardNumber(),
                paymentRequest.getName()
        );
        paymentRepository.save(payment);
        return ResponseEntity.ok(payment);
    }

}
