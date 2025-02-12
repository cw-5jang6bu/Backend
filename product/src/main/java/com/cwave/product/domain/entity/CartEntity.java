package com.cwave.product.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@Table(name = "cart")
@NoArgsConstructor
@AllArgsConstructor
public class CartEntity {

    /**
     * 장바구니 고유 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;
    /**
     * 이메일 주소
     */
    @Column(name = "member_id")
    private Long memberId;
    /**
     * 주문 상품 개수
     */
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<CartItemEntity> items = new ArrayList<>();

}
