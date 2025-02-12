package com.cwave.member.service;

import com.cwave.member.entity.Member;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public interface MemberService {
    List<Member> getAllMemberList();
    Member registerMember(Member member); // 회원가입
    Member loginMember(Member member); // 로그인
    //void deleteMember(Long id); // 탈퇴
}
