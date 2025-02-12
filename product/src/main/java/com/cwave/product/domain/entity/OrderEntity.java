package com.cwave.product.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Entity
@Table(name = "order")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Order {

    /**
     * 주문 고유 id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
    /**
     * 주문한 회원 email
     */
    @Column(name = "member_email")
    private String memberEmail;
    /**
     * 주문 배송 주소
     */
    @Column(name = "address")
    private String address;
    /**
     * 주문자 이름
     */
    @Column(name = "name")
    private String name;
    /**
     * 주문한 시각
     */
    @CreationTimestamp
    @Column(name = "order_time")
    private LocalDateTime orderTime;
    /**
     * 주문 취소여부
     */
    @Column(name = "is_active")
    private boolean isActive;

    @Builder
    public Order(String memberEmail, String address, String name) {
        this.memberEmail = memberEmail;
        this.address = address;
        this.name = name;
        isActive = true;
    }

    public void cancel() {
        this.isActive = false;
    }

}
