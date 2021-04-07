package com.example.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MyOrderRequestDto {
    private Long id;
    private String img_url;
    private String product_name;
    private String price;
    private String username;
    private Integer amount;
}

