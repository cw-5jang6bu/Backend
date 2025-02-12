package com.cwave.member.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "member")
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    /**
     * 고객 고유 id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    private String email;
    private String password;
}
