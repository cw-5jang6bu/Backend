package com.cwave.product.domain.entity;

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
@Builder
@Table(name = "`order`")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OrderEntity {

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
    @Column(name = "order_member_id")
    private Long memberId;
    /**
     * 주문 배송 주소
     */
    @Column(name = "order_address")
    private String address;
    /**
     * 주문자 이름
     */
    @Column(name = "order_member_name")
    private String name;
    /**
     * 주문한 시각
     */
    @CreationTimestamp
    @Column(name = "order_time")
    private LocalDateTime orderTime;
    /**
     * 총 가격
     */
    @Column(name = "order_total_amount")
    private Integer totalAmount;
}
