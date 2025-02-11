package com.cwave.product.order.service;

import com.cwave.product.order.dto.CreateOrderDto;
import com.cwave.product.order.entity.Order;
import java.util.List;

public interface OrderService {

    void createOrder(CreateOrderDto dto);
    List<Order> getAllOrderList();
    void cancelOrder(Long id);
}
