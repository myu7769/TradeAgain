package com.zerobase.trade.domain.photo;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class PhotoRequestForm {

    private Long[] order;

    private PhotoContext photoContext;

    private Long contextId;
}
