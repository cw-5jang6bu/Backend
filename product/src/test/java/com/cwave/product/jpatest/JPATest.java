package com.cwave.product.jpatest;

import com.cwave.product.config.MemberClient;
import com.cwave.product.domain.Cart;
import com.cwave.product.domain.CartItem;
import com.cwave.product.domain.Member;
import com.cwave.product.domain.Product;
import com.cwave.product.domain.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@Rollback(false)
@SpringBootTest
public class JPATest {

    Logger log = LoggerFactory.getLogger(JPATest.class);

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MemberClient memberClient;

    @Test
    @DisplayName("Product 테이블 만들기")
    public void testCreateProduct() {
        ProductEntity product = ProductEntity.builder()
                .name("Test Product")
                .price(5000)
                .stock(50)
                .build();

        entityManager.persist(product);
        entityManager.flush();

        assertThat(product.getId()).isNotNull();
    }

    @Test
    @DisplayName("Cart 테이블 만들기")
    public void testCreateCartWithItems() {
        ProductEntity product = ProductEntity.builder()
                .name("Test Product")
                .price(5000)
                .stock(50)
                .build();

        entityManager.persist(product);

        CartEntity cart = CartEntity.builder()
                .memberId(1L)
                .build();
        entityManager.persist(cart);

        CartItemEntity cartItem = CartItemEntity.builder()
                .product(product)
                .quantity(2)
                .cart(cart)
                .build();

        cart.getItems().add(cartItem);
        entityManager.persist(cartItem);
        entityManager.flush();
        entityManager.clear();

        CartEntity persistedCart = entityManager.find(CartEntity.class, cart.getId());

        assertThat(persistedCart.getId()).isNotNull();
        assertThat(persistedCart.getItems()).hasSize(1);
    }

    @Test
    @DisplayName("Order와 Payment 테이블 만들기")
    public void testCreateOrderAndPayment() {

        OrderEntity order = OrderEntity.builder()
                .memberId(1L)
                .address("example")
                .name("exampleName")
                .orderTime(LocalDateTime.now())
                .build();

        entityManager.persist(order);
        entityManager.flush();

        assertThat(order.getId()).isNotNull();
        assertThat(order.getAddress()).isEqualTo("example");
    }

    @Test
    @DisplayName("다른 DB에서 MemberId가져오기")
    public void testGetMemberById() {
        Long memberId = 1L;

        Member member = memberClient.getMemberById(memberId);  // ✅ 정상 호출

        log.info(member.getEmail().toString());
        assertThat(member).isNotNull();
    }

}
