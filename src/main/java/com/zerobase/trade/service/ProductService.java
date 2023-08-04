package com.zerobase.trade.service;

import static com.zerobase.trade.exception.ErrorCode.ALREADY_REGISTER_ACCOUNT;
import static com.zerobase.trade.exception.ErrorCode.ALREADY_REGISTER_EMAIL;
import static com.zerobase.trade.exception.ErrorCode.ALREADY_REGISTER_PHONE;
import static com.zerobase.trade.exception.ErrorCode.NOT_FOUND_USER;
import static com.zerobase.trade.exception.ErrorCode.NOT_MATCH_ID_PASSWORD;

import com.zerobase.trade.domain.entity.Member;
import com.zerobase.trade.domain.entity.Product;
import com.zerobase.trade.domain.member.MemberSignInForm;
import com.zerobase.trade.domain.member.MemberSignUpForm;
import com.zerobase.trade.domain.product.productCreateRequestForm;
import com.zerobase.trade.exception.CustomException;
import com.zerobase.trade.repository.MemberRepository;
import com.zerobase.trade.repository.ProductRepository;
import com.zerobase.trade.repository.redis.RedisMemberRepository;
import com.zerobase.trade.security.token.JwtAuthenticationProvider;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    public boolean productCreate(productCreateRequestForm form, String token) {

        Member member = memberRepository.findByAccount(jwtAuthenticationProvider.getUserAccount(token))
            .orElseThrow(() -> new CustomException(NOT_FOUND_USER));


        productRepository.save(Product.of(form,member));
        return true;
    }
}
