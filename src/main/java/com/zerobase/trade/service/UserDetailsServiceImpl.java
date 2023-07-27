package com.zerobase.trade.service;

import com.zerobase.trade.exception.CustomException;
import com.zerobase.trade.exception.ErrorCode;
import com.zerobase.trade.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final MemberRepository memberRepository;


  //

  @Override
  public UserDetails loadUserByUsername(String account) {

    return memberRepository.findByAccount(account).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
  }
}