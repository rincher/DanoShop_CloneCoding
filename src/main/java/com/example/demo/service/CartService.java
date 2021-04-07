package com.example.demo.service;

import com.example.demo.Dto.CartRequestDto;
import com.example.demo.domain.Cart;
import com.example.demo.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;


    @Transactional
    public Cart createCart(CartRequestDto cartRequestDto, String username){
        Cart cart = new Cart(cartRequestDto, username);
        cartRepository.save(cart);
        return cart;
    }
}
