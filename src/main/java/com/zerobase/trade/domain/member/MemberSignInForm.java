package com.zerobase.trade.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class MemberSignInForm {

    private String account;
    private String password;
}
