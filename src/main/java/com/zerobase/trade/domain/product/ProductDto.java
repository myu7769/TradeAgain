package com.zerobase.trade.domain.product;

import com.zerobase.trade.domain.entity.Product;
import java.util.List;
import javax.persistence.Lob;
import lombok.Builder;

@Builder
public class ProductDto {

  private Long id;
  private String title;
  // TODO: 2023-07-25 content 입력 시 글자 수 제한 exception 필요
  @Lob
  private String content;
  private String keywords;

  public static ProductDto from(Product product) {
    return ProductDto.builder()
        .id(product.getId())
        .title(product.getTitle())
        .content(product.getContent())
        .keywords(product.getKeywords())
        .build();
  }
}
