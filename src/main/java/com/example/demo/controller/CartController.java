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

    @PostMapping("/api/cart")
    public Cart createCart(@RequestBody CartRequestDto cartRequestDto){
        String username = cartRequestDto.getUsername();

        System.out.println(cartRequestDto.getUsername());
        System.out.println(cartRequestDto.getProduct_name());
        System.out.println(cartRequestDto.getPrice());
        System.out.println(cartRequestDto.getImg_url());

        Cart cart = cartService.createCart(cartRequestDto, username);
        return cart;
    }

    @GetMapping("/api/cart/{username}")
    public List<Cart> getCart(@PathVariable String username){
        return cartService.getCartByUsername(username);
    }

    @DeleteMapping("/api/cart/{username}/removeItem/{id}")
    public void removeitem(@PathVariable String username, @PathVariable Long id){
        cartService.deleteItem(username, id);}
}
