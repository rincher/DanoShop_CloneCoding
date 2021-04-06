package com.example.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductRequestDto {
    private Long id;
    private String image_url;
    private String product_name;
    private String price;
    private boolean isTrending;
    private boolean isNew;
    private boolean isDano;
    private boolean isBestDeal;
    private boolean isFree;
}
