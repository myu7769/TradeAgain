package com.zerobase.trade.service;

import com.zerobase.trade.domain.entity.Member;
import com.zerobase.trade.domain.member.MemberSignInForm;
import com.zerobase.trade.domain.member.MemberSignUpForm;
import com.zerobase.trade.exception.CustomException;
import com.zerobase.trade.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static com.zerobase.trade.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public Member customerSignUp(MemberSignUpForm form){

        if (memberRepository.findByAccount(form.getAccount().toLowerCase(Locale.ROOT)).isPresent()) {
            throw new CustomException(ALREADY_REGISTER_USER);
        }else{
            return memberRepository.save(Member.from(form));
        }
    };

    public Optional<Member> customerSignIn(MemberSignInForm form) {

        Member member = memberRepository.findByAccount(form.getAccount())
                .orElseThrow(()-> new CustomException(NOT_FOUND_USER));
        // TODO: 2023-07-24 pw 매칭 구현 필요


        return Optional.ofNullable(member);
    }
}
