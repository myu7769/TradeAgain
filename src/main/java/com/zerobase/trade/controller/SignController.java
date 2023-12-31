package com.zerobase.trade.controller;


import com.zerobase.trade.domain.entity.Member;
import com.zerobase.trade.domain.member.MemberDeleteRequestForm;
import com.zerobase.trade.domain.member.MemberSignInForm;
import com.zerobase.trade.domain.member.MemberSignUpForm;
import com.zerobase.trade.exception.CustomException;
import com.zerobase.trade.security.token.JwtAuthenticationProvider;
import com.zerobase.trade.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.zerobase.trade.exception.ErrorCode.NOT_FOUND_USER;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class SignController {


    private final MemberService memberService;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

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


    @DeleteMapping("/delete")
    public ResponseEntity<String> memberDelete(@RequestHeader("Authorization") String token,
                                               @RequestBody MemberDeleteRequestForm form ){

        memberService.memberDelete(token, form);

        return ResponseEntity.ok().body("delete ok");
    }

}
