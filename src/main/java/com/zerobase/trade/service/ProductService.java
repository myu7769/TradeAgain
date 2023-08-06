package com.zerobase.trade.service;


import com.zerobase.trade.domain.entity.Member;
import com.zerobase.trade.domain.entity.Product;
import com.zerobase.trade.domain.product.ProductDeleteRequestForm;
import com.zerobase.trade.domain.product.ProductDto;
import com.zerobase.trade.domain.product.productRequestForm;
import com.zerobase.trade.domain.product.productUpdateRequestForm;
import com.zerobase.trade.exception.CustomException;
import com.zerobase.trade.repository.MemberRepository;
import com.zerobase.trade.repository.ProductRepository;
import com.zerobase.trade.security.token.JwtAuthenticationProvider;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.zerobase.trade.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    public ProductDto productCreate(productRequestForm form, String token) {

        Member member = memberRepository.findByAccount(jwtAuthenticationProvider.getUserAccount(token))
            .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        if(!member.getAccount().equals(form.getAccount())){
            throw new CustomException(NOT_VALID_TOKEN);
        }

        Product product = productRepository.save(Product.of(form,member));

        return ProductDto.from(product);
    }

    public List<ProductDto> findAll(Pageable pageable) {

        List<ProductDto> productDtos = productRepository.findAll(pageable)
            .getContent()
            .stream()
            .map(ProductDto::from)
            .collect(Collectors.toList());

        return productDtos;
    }

    @Transactional
    public ProductDto productUpdate(productUpdateRequestForm form, String token) {

        Member member = memberRepository.findByAccount(jwtAuthenticationProvider.getUserAccount(token))
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        if(!member.getAccount().equals(form.getAccount())){
            throw new CustomException(NOT_VALID_TOKEN);
        }

        Product product = productRepository.findById(form.getId())
                .orElseThrow(() -> new CustomException(PRODUCT_NOT_FOUND));

        product.setContent(form.getContent());
        product.setTitle(form.getTitle());
        product.setKeywords(form.getKeyword());

        return ProductDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .content(product.getContent())
                .keywords(product.getKeywords())
                .build();
    }

    public boolean productDelete(ProductDeleteRequestForm form, String token) {

        Member member = memberRepository.findByAccount(jwtAuthenticationProvider.getUserAccount(token))
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        if(!member.getAccount().equals(form.getAccount())){
            throw new CustomException(NOT_VALID_TOKEN);
        }

        Optional<Product> product = productRepository.findById(form.getId());

        if(product.isPresent()){
            productRepository.deleteById(form.getId());
        } else {
            throw new CustomException(PRODUCT_NOT_FOUND);
        }

        return true;
    }
}
