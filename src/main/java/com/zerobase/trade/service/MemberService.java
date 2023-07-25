package com.zerobase.trade.service;

import com.zerobase.trade.domain.entity.Member;
import com.zerobase.trade.domain.member.MemberSignInForm;
import com.zerobase.trade.domain.member.MemberSignUpForm;
import com.zerobase.trade.exception.CustomException;
import com.zerobase.trade.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;


import static com.zerobase.trade.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

//    private final RedisTemplate<String, String> redisTemplate;


    public Member memberSignUp(MemberSignUpForm form){

        if (memberRepository.findByAccount(form.getAccount().toLowerCase(Locale.ROOT)).isPresent()) {
            throw new CustomException(ALREADY_REGISTER_ACCOUNT);
        }

        if (memberRepository.findByEmail(form.getEmail()).isPresent()) {
            throw new CustomException(ALREADY_REGISTER_EMAIL);
        }

        if (memberRepository.findByPhone(form.getPhone()).isPresent()) {
            throw new CustomException(ALREADY_REGISTER_PHONE);
        }

        Member member = Member.of(form, passwordEncoder.encode(form.getPassword()));
//        redisTemplate.opsForValue().set(member.getAccount() , member.getAccount());
        return memberRepository.save(member);

    };

    public Member memberSignIn(MemberSignInForm form) {

        Member member = memberRepository.findByAccount(form.getAccount())
            .orElseThrow(()-> new CustomException(NOT_FOUND_USER));

        if(!passwordEncoder.matches(form.getPassword(),member.getPassword())){
            throw new CustomException(NOT_MATCH_ID_PASSWORD);
        }

        return member;
    }
}
