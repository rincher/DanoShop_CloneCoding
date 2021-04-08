package com.example.demo.controller;

import com.example.demo.Dto.CartRequestDto;
import com.example.demo.domain.Cart;
import com.example.demo.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CartController {
    private final CartService cartService;

    // 쇼핑카트에 상품 추가
    @PostMapping("/api/cart")
    public Cart createCart(@RequestBody CartRequestDto cartRequestDto){
        String username = cartRequestDto.getUsername();
        Cart cart = cartService.createCart(cartRequestDto, username);
        return cart;
    }

    //사용자 별 쇼핑카트 내역 출력
    @GetMapping("/api/cart/{username}")
    public List<Cart> getCart(@PathVariable String username){
        return cartService.getCartByUsername(username);
    }

    //사용자 별 쇼핑카트애서 상품 빼기
    @DeleteMapping("/api/cart/{username}/removeItem/{id}")
    public void removeitem(@PathVariable String username, @PathVariable Long id){
        cartService.deleteItem(username, id);}
}
