package com.zerobase.trade.domain.member;

import com.zerobase.trade.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Id;
import java.util.*;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "member", timeToLive = 3600)
public class MemberDto implements UserDetails {


    @Id
    private String id;
    @Indexed
    private String account;

    private String email;

    private String name;

    private String phone;

    private List<String> roles = new ArrayList<>();

    public static MemberDto from(Member form) {

        return new MemberDto().builder()
                .account(form.getAccount().toLowerCase(Locale.ROOT))
                .email(form.getEmail())
                .name(form.getName())
                .phone(form.getPhone())
                .roles(form.getRoles())
                .build();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
