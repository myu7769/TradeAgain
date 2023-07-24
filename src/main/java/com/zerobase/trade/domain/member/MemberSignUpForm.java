package com.zerobase.trade.domain.member;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberSignUpForm {

    private String account;
    private String email;
    private String name;
    private String password;
    private String phone;
}
