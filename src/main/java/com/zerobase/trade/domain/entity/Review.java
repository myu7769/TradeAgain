package com.zerobase.trade.domain.entity;

import com.zerobase.trade.domain.model.BaseEntity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review")
public class Review extends BaseEntity {
    @Id
    @Column(name ="review_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToOne
//    @JoinColumn(name = "member_id")
    private String reviewer;

    // TODO: 2023-07-25 content 입력 시 글자 수 제한 exception 필요 
    @Lob
    private String content;
    private Double score;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
