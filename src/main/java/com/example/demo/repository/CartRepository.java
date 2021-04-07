package com.example.demo.repository;

import com.example.demo.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, String> {

    Optional<Cart> findTopByUsernameOrderByCreatedAtDesc(String Username);
}
