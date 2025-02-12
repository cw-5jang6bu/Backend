package com.cwave.product.service;

import com.cwave.product.domain.entity.OrderEntity;

public interface OrderService {

    OrderEntity createOrder(Long memberId, String address, String name, Integer totalAmount);
}
