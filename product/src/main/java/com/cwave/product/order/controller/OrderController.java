package com.cwave.product.order.controller;

import com.cwave.product.common.template.BaseResponse;
import com.cwave.product.order.dto.CreateOrderDto;
import com.cwave.product.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderDto order) {
        orderService.createOrder(order);
        return BaseResponse.ofSuccess();
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        return BaseResponse.ofSuccess(orderService.getAllOrderList());
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return BaseResponse.ofSuccess();
    }
}
