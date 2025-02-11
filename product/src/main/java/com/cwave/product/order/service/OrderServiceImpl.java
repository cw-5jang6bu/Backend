package com.cwave.product.order.service;

import com.cwave.product.item.repository.ItemRepository;
import com.cwave.product.order.dto.CreateOrderDto;
import com.cwave.product.order.entity.Order;
import com.cwave.product.order.entity.OrderItem;
import com.cwave.product.order.repository.OrderItemRepository;
import com.cwave.product.order.repository.OrderRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;

    @Override
    public void createOrder(CreateOrderDto dto) {
        Order newOrder = orderRepository.save(Order.builder()
                .memberId(dto.getMemberId())
                .address(dto.getAddress())
                .name(dto.getName())
                .build());
        List<OrderItem> orderItems = dto.getItemList().stream()
            .map(itemDto -> OrderItem.builder()
                .count(itemDto.getCount())
                .item(itemRepository.findById(itemDto.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found: " + itemDto.getItemId())))
                .order(newOrder)
                .build())
            .collect(Collectors.toList());

        orderItemRepository.saveAll(orderItems);
    }

    @Override
    public List<Order> getAllOrderList() {
        return orderRepository.findAll();
    }


    @Override
    public void cancelOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }
}
