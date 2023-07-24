package com.zerobase.trade.controller;


import com.zerobase.trade.domain.entity.Member;
import com.zerobase.trade.domain.member.MemberSignInForm;
import com.zerobase.trade.domain.member.MemberSignUpForm;
import com.zerobase.trade.exception.CustomException;
import com.zerobase.trade.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.zerobase.trade.exception.ErrorCode.LOGIN_CHECK_FAIL;


@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class SignController {


    private final MemberService memberService;

    @PostMapping("/signUp")
    public ResponseEntity<Member> memberSignUp(@RequestBody MemberSignUpForm form) {
        return ResponseEntity.ok(memberService.memberSignUp(form));
    }

    @PutMapping("/signIn")
    public ResponseEntity<Member> memberSignIn(@RequestBody MemberSignInForm form) {
        Member member = memberService.memberSignIn(form);


        return ResponseEntity.ok(member);
        // TODO: 2023-07-24 memberSignInResponse
    }
}
