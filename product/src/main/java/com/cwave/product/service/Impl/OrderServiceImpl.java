package com.cwave.product.service.Impl;

import com.cwave.product.domain.entity.OrderEntity;
import com.cwave.product.repository.OrderRepository;
import com.cwave.product.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public OrderEntity createOrder(Long memberId, String address, String name, Integer totalAmount) {
        OrderEntity order = OrderEntity.builder()
                .memberId(memberId)
                .address(address)
                .name(name)
                .totalAmount(totalAmount)
                .build();

        return orderRepository.save(order);
    }

}
