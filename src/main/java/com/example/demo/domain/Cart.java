package com.example.demo.domain;

import com.example.demo.Dto.CartRequestDto;
import com.example.demo.Dto.ProductRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Cart extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String img_url;

    @Column(nullable = false)
    private String product_name;

    @Column(nullable = false)
    private String price;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private String username;

    public Cart(CartRequestDto cartRequestDto, String username){
        this.username = username;
        this.img_url = cartRequestDto.getImage_url();
        this.product_name = cartRequestDto.getProduct_name();
        this.price = cartRequestDto.getPrice();
    }

}

