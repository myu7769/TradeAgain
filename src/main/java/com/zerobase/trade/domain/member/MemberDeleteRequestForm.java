package com.zerobase.trade.domain.member;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDeleteRequestForm {

    private Long id;
    private String account;
}
