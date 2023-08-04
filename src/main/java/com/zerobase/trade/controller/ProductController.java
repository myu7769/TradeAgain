package com.zerobase.trade.controller;


import com.zerobase.trade.domain.entity.Member;
import com.zerobase.trade.domain.product.ProductDto;
import com.zerobase.trade.domain.product.productCreateRequestForm;
import com.zerobase.trade.domain.product.productSearchRequestForm;
import com.zerobase.trade.security.token.JwtAuthenticationProvider;
import com.zerobase.trade.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {


    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductDto> productCreate(@RequestHeader("Authorization") String token,
                                            @RequestBody productCreateRequestForm form) {

        return ResponseEntity.ok().body(productService.productCreate(form, token));
    }

    @GetMapping("/search")
    public List<ProductDto> productSearch(Pageable pageable) {
        return productService.findAll(pageable);
    }

//    @PostMapping("/update")
//    public ResponseEntity<ProductResponse> productUpdate(@RequestHeader("Authorization") String token,
//        @RequestBody productUpdateRequestForm form) {
//        return null;
//    }

//    @DeleteMapping("/delete")
//    public ResponseEntity<String> productDelete(@RequestHeader("Authorization") String token,
//        @RequestBody productDeleteRequestForm form){
//
//        return ResponseEntity.ok().body("delete ok");;
//    }
}
