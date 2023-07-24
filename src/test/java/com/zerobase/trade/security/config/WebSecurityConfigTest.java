package com.zerobase.trade.security.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class WebSecurityConfigTest {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Test
  public void EncoderAndMatch() {
    // username에 해당하는 사용자가 이미 있는지 확인합니다.
    //given
    String password = "1231231313pwerwrwrr1";
    // 비밀번호를 암호화합니다.
    //when
    String encodedPassword = passwordEncoder.encode(password);


    //then
    assertTrue(passwordEncoder.matches("1231231313pwerwrwrr1", encodedPassword));

  }

}