package com.example.demo.controller;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class ProductController {
    private final ProductRepository productRepository;

    //모든 상품 조회
    @GetMapping("/api/product")
    public List<Product> getProduct(){
        return productRepository.findAll();
    }
}
