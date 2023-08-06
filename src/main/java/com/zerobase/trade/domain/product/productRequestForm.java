package com.zerobase.trade.domain.product;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class productRequestForm {

  private String account;
  private String title;
  private String content;
  private String keyword;

  private int price;
  private boolean discountYn;
}
