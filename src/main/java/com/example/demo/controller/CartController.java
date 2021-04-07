package com.example.demo.controller;

import com.example.demo.Dto.CartRequestDto;
import com.example.demo.domain.Cart;
import com.example.demo.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class CartController {
    private final CartService cartService;

    @PostMapping("/api/cart")
    public Cart createCart(@RequestBody CartRequestDto cartRequestDto){
        String username = cartRequestDto.getUsername();

        Cart cart = cartService.createCart(cartRequestDto, username);
        return cart;
    }

    @GetMapping("/api/cart/{username}")
    public Optional<Cart> getCart(@PathVariable String username){
        return cartService.getCartByUsername(username);
    }
}
