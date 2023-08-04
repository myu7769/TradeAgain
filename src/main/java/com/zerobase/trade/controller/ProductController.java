package com.zerobase.trade.controller;


import com.zerobase.trade.domain.entity.Member;
import com.zerobase.trade.domain.product.productCreateRequestForm;
import com.zerobase.trade.security.token.JwtAuthenticationProvider;
import com.zerobase.trade.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public ResponseEntity<String> productCreate(@RequestHeader("Authorization") String token,
                                            @RequestBody productCreateRequestForm form) {

        productService.productCreate(form, token);

        return ResponseEntity.ok().body("create ok");
    }

//    @PostMapping("/search")
//    public ResponseEntity<ProductResponse> productSearch(@RequestBody productSearchRequestForm form) {
//        return null;
//    }

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
