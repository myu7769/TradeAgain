package com.zerobase.trade.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "member")
public class Member{
    @Id
    @Column(name ="member_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String name;
    private String password;
    @Column(unique = true)
    private String phone;
    private boolean verify;
    private String accountStatus;

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
