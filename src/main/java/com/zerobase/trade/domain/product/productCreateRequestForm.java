package com.zerobase.trade.domain.product;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class productCreateRequestForm {


  private String account;
  private String title;
  private String content;

  private String keyword;

  private int price;
  private boolean discountYn;
}
