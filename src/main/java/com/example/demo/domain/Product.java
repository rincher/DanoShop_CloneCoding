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

    @Column(nullable = false)
    private boolean isTrending;

    @Column(nullable = false)
    private boolean isNew;

    @Column(nullable = false)
    private boolean isDano;

    @Column(nullable = false)
    private boolean isBestDeal;

    @Column(nullable = false)
    private boolean isFree;

    public Product(ProductRequestDto requestDto, Long userId){
        this.id = requestDto.getId();
        this.image_url = requestDto.getImage_url();
        this.product_name = requestDto.getProduct_name();
        this.price = requestDto.getPrice();
        this.isTrending = requestDto.isTrending();
        this.isNew = requestDto.isNew();
        this.isDano = requestDto.isDano();
        this.isBestDeal = requestDto.isBestDeal();
        this.isFree = requestDto.isFree();
    }
}
