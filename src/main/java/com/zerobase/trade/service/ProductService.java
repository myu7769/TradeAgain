package com.zerobase.trade.service;


import static com.zerobase.trade.exception.ErrorCode.NOT_FOUND_USER;


import com.zerobase.trade.domain.entity.Member;
import com.zerobase.trade.domain.entity.Product;
import com.zerobase.trade.domain.product.ProductDto;
import com.zerobase.trade.domain.product.productCreateRequestForm;
import com.zerobase.trade.exception.CustomException;
import com.zerobase.trade.repository.MemberRepository;
import com.zerobase.trade.repository.ProductRepository;
import com.zerobase.trade.repository.redis.RedisMemberRepository;
import com.zerobase.trade.security.token.JwtAuthenticationProvider;
import java.net.ContentHandler;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    public ProductDto productCreate(productCreateRequestForm form, String token) {

        Member member = memberRepository.findByAccount(jwtAuthenticationProvider.getUserAccount(token))
            .orElseThrow(() -> new CustomException(NOT_FOUND_USER));


        Product product = productRepository.save(Product.of(form,member));

        return ProductDto.builder()
                .id(product.getId())
                .keywords(product.getKeywords())
                .content(product.getContent())
                .title(product.getTitle())
                .build();
    }

    public List<ProductDto> findAll(Pageable pageable) {

        List<ProductDto> productDtos = productRepository.findAll(pageable)
            .getContent()
            .stream()
            .map(ProductDto::from)
            .collect(Collectors.toList());

        return productDtos;
    }

  }
