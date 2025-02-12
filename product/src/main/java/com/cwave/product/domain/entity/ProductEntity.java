package com.cwave.product.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class ProductsEntity {
    /**
     * 상품 고유 id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    /**
     * 상품 명
     */
    @Column(name = "product_name")
    private String name;
    /**
     * 상품 재고 수량
     */
    @Column(name = "product_stock")
    private Integer stock;
    /**
     * 상품 가격
     */
    @Column(name = "product_price")
    private int price;

}
