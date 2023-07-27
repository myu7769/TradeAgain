package com.zerobase.trade.controller;


import com.zerobase.trade.domain.entity.Member;
import com.zerobase.trade.domain.member.MemberSignInForm;
import com.zerobase.trade.domain.member.MemberSignUpForm;
import com.zerobase.trade.exception.CustomException;
import com.zerobase.trade.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class SignController {


    private final MemberService memberService;

    @PostMapping("/signUp")
    public ResponseEntity<Member> memberSignUp(@RequestBody MemberSignUpForm form) {
        return ResponseEntity.ok(memberService.memberSignUp(form));
    }

    @PostMapping("/signIn")
    public ResponseEntity<String> memberSignIn(@RequestBody MemberSignInForm form) {

        return ResponseEntity.ok()
            .header("Authorization", memberService.memberSignIn(form))
            .body("User successfully logged in.");
        // TODO: 2023-07-24 memberSignInResponse
    }

}
