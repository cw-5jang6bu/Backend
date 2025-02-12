package com.cwave.member.controller;


import com.cwave.member.common.template.BaseResponse;
import com.cwave.member.entity.Member;
import com.cwave.member.repository.MemberRepository;
import com.cwave.member.service.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@Transactional
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/list")
    public ResponseEntity<?> getAllMembersList() {
        return BaseResponse.ofSuccess(memberService.getAllMemberList());
    }

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<?> registerMember(@RequestBody Member member) {
        //log.info("register member: {}", member);
        return BaseResponse.ofSuccess(memberService.registerMember(member));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> loginMember(@RequestBody Member member) {
        //log.info("loginMember: {}", member.getEmail());
        return BaseResponse.ofSuccess(memberService.loginMember(member));
    }

    // MemberEntity 공유
    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Long id) {
        Optional<Member> member = memberRepository.findById(id);
        return member.orElseThrow(() -> new RuntimeException("Member Not Found!"));
    }

//    // 회원 탈퇴 -> 나중에 구현
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deleteMember(@PathVariable Long id) {
//        memberService.deleteMember(id);
//        return BaseResponse.ofSuccess("Member deleted successfully");
//    }

}
