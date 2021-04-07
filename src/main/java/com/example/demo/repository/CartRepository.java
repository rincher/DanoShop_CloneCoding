package com.example.demo.repository;

import com.example.demo.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CartRepository extends JpaRepository<Cart, String> {

    List<Cart> findAllByUsernameOrderByCreatedAtDesc(String Username);
//    List<Cart> findAllByUsername(String Username);

    List<Cart>findAllByUsername(String username);

    void deleteByIdAndUsername(Long id, String username);

    void deleteAllByUsername(String username);
}
