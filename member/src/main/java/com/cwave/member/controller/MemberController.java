package com.cwave.member.controller;


import com.cwave.member.common.template.BaseResponse;
import com.cwave.member.entity.Member;
import com.cwave.member.service.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Transactional
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllMembersList() {
        return BaseResponse.ofSuccess(memberService.getAllMemberList());
    }

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<?> registerMember(@RequestBody Member member) {
        return BaseResponse.ofSuccess(memberService.registerMember(member));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> loginMember(@RequestBody Member member) {
        return BaseResponse.ofSuccess(memberService.loginMember(member));
    }

    // 회원 탈퇴
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return BaseResponse.ofSuccess("Member deleted successfully");
    }

}
