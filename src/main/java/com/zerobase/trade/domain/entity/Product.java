package com.zerobase.trade.domain.entity;

import com.zerobase.trade.domain.model.BaseEntity;
import com.zerobase.trade.domain.product.productRequestForm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product extends BaseEntity {
    @Id
    @Column(name ="product_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    // TODO: 2023-07-25 content 입력 시 글자 수 제한 exception 필요
    @Lob
    private String content;
    private String keywords;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Product of(productRequestForm form, Member member) {

        return Product.builder()
            .title(form.getTitle())
            .content(form.getContent())
            .keywords(form.getKeyword())
            .member(member)
            .build();
    }

}
