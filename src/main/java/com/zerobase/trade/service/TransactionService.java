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
import com.zerobase.trade.repository.TransactionRepository;
import com.zerobase.trade.security.token.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.zerobase.trade.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
}
