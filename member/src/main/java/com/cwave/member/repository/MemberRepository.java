package com.cwave.member.repository;


import com.cwave.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // repository 어노테이션을 해주면 데이터 접근 관련 예외 처리가 쉽고, DAO를 직접 구현했다면 꼭 필요.
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}