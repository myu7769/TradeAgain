package com.zerobase.trade.domain.entity;

import com.zerobase.trade.domain.member.AccountStatus;
import com.zerobase.trade.domain.member.MemberSignUpForm;
import com.zerobase.trade.domain.model.BaseEntity;
import com.zerobase.trade.domain.product.productCreateRequestForm;
import java.util.Collections;
import java.util.Locale;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Product of(productCreateRequestForm form,Member member) {

        return Product.builder()
            .title(form.getTitle())
            .content(form.getContent())
            .keywords(form.getKeyword())
            .member(member)
            .build();
    }

}
