package com.cwave.product.service;

import com.cwave.product.domain.entity.*;
import com.cwave.product.repository.CartItemRepository;
import com.cwave.product.repository.CartRepository;
import com.cwave.product.repository.OrderRepository;
import com.cwave.product.repository.PaymentRepository;
import com.cwave.product.service.Impl.CartServiceImpl;
import com.cwave.product.service.Impl.OrderServiceImpl;
import com.cwave.product.service.Impl.PaymentServiceImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
@Rollback(false)
public class serviceTest {

    Logger log = LoggerFactory.getLogger(serviceTest.class);

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    @InjectMocks
    private OrderServiceImpl orderService;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    @DisplayName("MemberId로 CartItemList 가쟈오기")
    public void testGetAllCartItemListByMemberId() {

        //Given
        Long memberId = 1L;

        CartEntity cart = CartEntity.builder().id(1L).memberId(memberId).build();
        ProductEntity product = ProductEntity.builder().id(101L).name("Test Product").build();
        CartItemEntity cartItem = CartItemEntity.builder().cart(cart).product(product).quantity(1).build();

        //When
        when(cartRepository.findByMemberId(memberId)).thenReturn(cart);
        when(cartItemRepository.findByCartId(cart.getId())).thenReturn(List.of(cartItem));

        List<ProductEntity> products = cartService.getAllCartItemListByMemberId(memberId);

        //Then
        assertThat(products).isNotNull();
        assertThat(products.size()).isEqualTo(1);
        assertThat(products.get(0).getName()).isEqualTo("Test Product");
    }

    @Test
    @DisplayName("Order 만들기")
    public void testCreateOrder() {
        Long memberId = 1L;
        String address = "Seoul, South Korea";
        String name = "John Doe";
        Integer totalAmount = 50000;

        when(orderRepository.save(any(OrderEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));  // ✅ 저장한 객체 반환

        OrderEntity createdOrder = orderService.createOrder(memberId, address, name, totalAmount);

        assertThat(createdOrder).isNotNull();                        // ✅ null 체크 통과
        assertThat(createdOrder.getMemberId()).isEqualTo(memberId);
        assertThat(createdOrder.getAddress()).isEqualTo(address);
        assertThat(createdOrder.getName()).isEqualTo(name);
        assertThat(createdOrder.getTotalAmount()).isEqualTo(totalAmount);
    }

    @Test
    @DisplayName("Payment 만들기")
    public void testCreatePayment() {
        // ✅ Given (테스트 데이터 준비)
        Long memberId = 1L;
        String address = "123 Main St";
        String cardNumber = "1234 5678 9101 1121";
        String name = "John Doe";

        PaymentEntity mockPayment = PaymentEntity.builder()
                .memberId(memberId)
                .address(address)
                .cardNumber(cardNumber)
                .memberName(name)
                .build();

        // ✅ When (Mock 동작 설정)
        when(paymentRepository.save(any(PaymentEntity.class))).thenReturn(mockPayment);

        // ✅ Act (서비스 메서드 호출)
        PaymentEntity createdPayment = paymentService.createPayment(memberId, address, cardNumber, name);

        // ✅ Then (검증)
        assertEquals(memberId, createdPayment.getMemberId());
        assertEquals(address, createdPayment.getAddress());
        assertEquals(cardNumber, createdPayment.getCardNumber());
        assertEquals(name, createdPayment.getMemberName());
    }
}
