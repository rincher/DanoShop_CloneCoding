package com.example.demo.domain;

import com.example.demo.Dto.ProductRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@RequiredArgsConstructor
public class Product {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String image_url;

    @Column(nullable = false)
    private String product_name;

    @Column(nullable = false)
    private String price;

    public Product(ProductRequestDto requestDto){
        this.id = requestDto.getId();
        this.image_url = requestDto.getImage_url();
        this.product_name = requestDto.getProduct_name();
        this.price = requestDto.getPrice();
    }
}
