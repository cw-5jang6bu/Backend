package com.cwave.product.controller;

import com.cwave.product.domain.OrderRequest;
import com.cwave.product.domain.entity.OrderEntity;
import com.cwave.product.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderRequest request) {
        OrderEntity order = orderService.createOrder(
                request.getMemberId(),
                request.getAddress(),
                request.getName(),
                request.getTotalAmount()
        );
        return ResponseEntity.ok(order);
    }
}

