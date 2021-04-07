package com.example.demo.domain;

import com.example.demo.Dto.CartRequestDto;
import com.example.demo.Dto.MyOrderRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLInsert;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class MyOrder extends Timestamped {

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

    public MyOrder(MyOrderRequestDto myOrderRequestDto, String username){
        this.username = username;
        this.img_url = myOrderRequestDto.getImg_url();
        this.product_name = myOrderRequestDto.getProduct_name();
        this.price = myOrderRequestDto.getPrice();
        this.amount = myOrderRequestDto.getAmount();
    }
}

