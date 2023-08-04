package com.zerobase.trade.controller;


import com.zerobase.trade.domain.product.ProductDeleteRequestForm;
import com.zerobase.trade.domain.product.ProductDto;
import com.zerobase.trade.domain.product.productRequestForm;
import com.zerobase.trade.domain.product.productUpdateRequestForm;
import com.zerobase.trade.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {


    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductDto> productCreate(@RequestHeader("Authorization") String token,
                                            @RequestBody productRequestForm form) {

        return ResponseEntity.ok().body(productService.productCreate(form, token));
    }

    @GetMapping("/search")
    public List<ProductDto> productSearch(Pageable pageable) {
        return productService.findAll(pageable);
    }

    @PostMapping("/update")
    public ResponseEntity<ProductDto> productUpdate(@RequestHeader("Authorization") String token,
        @RequestBody productUpdateRequestForm form) {

        return ResponseEntity.ok().body(productService.productUpdate(form, token));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> productDelete(@RequestHeader("Authorization") String token,
        @RequestBody ProductDeleteRequestForm form){

        productService.productDelete(form,token);

        return ResponseEntity.ok().body("delete ok");
    }
}
