package com.cwave.product.item.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Entity
@Table(name = "item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Item {

    /**
     * 상품 고유 id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;
    /**
     * 상품 명
     */
    @Column(name = "name")
    private String name;
    /**
     * 상품 재고 수량
     */
    @Column(name = "stock")
    private Long stock;
    /**
     * 상품 가격
     */
    @Column(name = "price")
    private Integer price;
    /**
     * 상품 이미지
     */
    @Column(name = "img_url")
    private String imgUrl;

}
