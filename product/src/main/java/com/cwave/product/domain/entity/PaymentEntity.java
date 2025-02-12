package com.cwave.product.domain.entity;

import com.cwave.product.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class PaymentEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "address")
    private String address;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "member_name")
    private String memberName;

}
