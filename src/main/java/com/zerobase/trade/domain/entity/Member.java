package com.zerobase.trade.domain.entity;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import com.zerobase.trade.domain.member.MemberSignUpForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class Member{
    @Id
    @Column(name ="member_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String account;
    @Column(unique = true)
    private String email;
    private String name;
    private String password;
    @Column(unique = true)
    private String phone;
    private boolean verify;
    private String accountStatus;

    public static Member from(MemberSignUpForm form) {

        return new Member().builder()
                .account(form.getAccount())
                .email(form.getEmail())
                .password(form.getPassword())
                .name(form.getName())
                .phone(form.getPhone())
                .verify(true)
                .accountStatus("approve")
                // TODO: 2023-07-24 status enum 생성 필요
                .build();
    }

/*    @OneToMany(mappedBy = "member",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Notification> notificationList = new ArrayList<>();
    @OneToMany(mappedBy = "member",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Trade> tradeList = new ArrayList<>();*/

}
