package com.zerobase.trade.domain.product;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductDeleteRequestForm {

    private Long id;
    private String account;
}
