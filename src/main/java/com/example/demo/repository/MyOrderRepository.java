package com.example.demo.repository;

import com.example.demo.domain.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyOrderRepository extends JpaRepository<MyOrder, Long> {
    List<MyOrder> findAllByUsernameOrderByCreatedAtDesc(String username);
}
