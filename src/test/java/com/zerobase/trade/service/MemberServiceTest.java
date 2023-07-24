package com.zerobase.trade.service;

import com.zerobase.trade.domain.entity.Member;
import com.zerobase.trade.domain.member.MemberSignInForm;
import com.zerobase.trade.domain.member.MemberSignUpForm;
import com.zerobase.trade.exception.CustomException;
import com.zerobase.trade.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static com.zerobase.trade.exception.ErrorCode.NOT_FOUND_USER;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class MemberServiceTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private MemberRepository memberRepository;
    @Test
    @Transactional
    public void member_SignUp() throws Exception {
        //given
        MemberSignUpForm createMember = MemberSignUpForm.builder()
                .account("id")
                .name("myung")
                .password("1234")
                .phone("12311111")
                .email("myung7769@gmail.com")
                .build();

        //when
        Long newMemberId = memberService.memberSignUp(createMember).getId();

        //then
        Member findMember = memberRepository.findById(newMemberId).get();

        assertEquals(createMember.getAccount(), findMember.getAccount());
        assertEquals(createMember.getName(), findMember.getName());
        assertEquals(createMember.getPassword(), findMember.getPassword());
        assertEquals(createMember.getPhone(), findMember.getPhone());
        assertEquals(createMember.getEmail(), findMember.getEmail());
    }

    @Test
    @Transactional
    public void member_SignIn() throws Exception {
        //given
        MemberSignUpForm createMember = MemberSignUpForm.builder()
                .account("id")
                .name("myung")
                .password("1234")
                .phone("12311111")
                .email("myung7769@gmail.com")
                .build();


        MemberSignInForm loginMember = MemberSignInForm.builder()
                .account(createMember.getAccount())
                .password(createMember.getPassword())
                .build();

        //when
        memberService.memberSignUp(createMember);

        //then
        Member member = memberService.memberSignIn(loginMember);

        assertEquals(createMember.getAccount(), member.getAccount());
        assertEquals(createMember.getName(), member.getName());
        assertTrue(passwordEncoder.matches(createMember.getPassword(), member.getPassword()));
        assertEquals(createMember.getPhone(), member.getPhone());
        assertEquals(createMember.getEmail(), member.getEmail());

    }
}