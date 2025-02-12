package com.cwave.product.order.entity;

import com.cwave.product.item.entity.Item;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "cart")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Cart {

    /**
     * 장바구니 고유 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;
    /**
     * 주문할 아이템
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    /**
     * 주문 상품 개수
     */
    @Column(name = "count")
    private Integer count;
    /**
     * 주문 정보
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Builder
    public Cart(Integer count, Item item, Order order) {
        this.count = count;
        this.item = item;
        this.order = order;
    }

}
