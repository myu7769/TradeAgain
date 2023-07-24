package com.zerobase.trade.domain.entity;

import com.zerobase.trade.domain.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chattingRoom")
public class ChattingRoom extends BaseEntity {
    @Id
    @Column(name ="chattingRoom_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO: 2023-07-25 content 입력 시 글자 수 제한 exception 필요
    @Lob
    private String messageContent;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
