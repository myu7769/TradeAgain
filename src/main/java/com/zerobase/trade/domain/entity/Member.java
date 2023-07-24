package com.zerobase.trade.domain.entity;


import com.zerobase.trade.domain.member.AccountStatus;
import java.util.Locale;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    public static Member from(MemberSignUpForm form) {

        return new Member().builder()
                .account(form.getAccount().toLowerCase(Locale.ROOT))
                .email(form.getEmail())
                .password(form.getPassword())
                .name(form.getName())
                .phone(form.getPhone())
                .accountStatus(AccountStatus.ACTIVE)
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
