package com.zerobase.trade.service;

import com.zerobase.trade.domain.entity.Member;
import com.zerobase.trade.domain.member.MemberDto;
import com.zerobase.trade.exception.CustomException;
import com.zerobase.trade.exception.ErrorCode;
import com.zerobase.trade.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final MemberRepository memberRepository;


  //

  @Override
  public UserDetails loadUserByUsername(String account) {

    Member member = memberRepository.findByAccount(account).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

    MemberDto memberDTO = MemberDto.builder()
            .account(member.getAccount())
            .name(member.getName())
            .email(member.getEmail())
            .phone(member.getPhone())
            .roles(member.getRoles())
            .build();

    return memberDTO;

  }
}