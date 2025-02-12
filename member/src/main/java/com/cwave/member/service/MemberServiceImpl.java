package com.cwave.member.service;

import com.cwave.member.entity.Member;
import com.cwave.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public List<Member> getAllMemberList() {
        return memberRepository.findAll();
    }

    @Override
    @Transactional
    public Member registerMember(Member member) {
        Member newMember = new Member();
        newMember.setEmail(member.getEmail());
        newMember.setPassword(member.getPassword());

        return memberRepository.save(newMember); // 회원 저장
    }

    @Override
    public Member loginMember(Member member) {
        Member existingMember = memberRepository.findByEmail(member.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        return existingMember; // 로그인 성공
    }

//    @Override
//    public void deleteMember(Long id) {
//        memberRepository.deleteById(id); // 회원 삭제
//    }
}
