package com.example.demo.controller;


import com.example.demo.Dto.MyOrderRequestDto;
import com.example.demo.domain.Cart;
import com.example.demo.domain.MyOrder;
import com.example.demo.service.MyOrderService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Getter
@RequiredArgsConstructor
@RestController
public class MyOrderController {
    private final MyOrderService myOrderService;

    @PostMapping("/api/MyOrder/{username}")
    public MyOrder createMyOrder(@PathVariable String username){
        MyOrder myOrder = myOrderService.createMyOrder(username);
        return myOrder;
    }
    @PostMapping("/api/DirectOrder/{username}")
    public MyOrder createDirectOrder(@PathVariable String username, @RequestBody MyOrderRequestDto myOrderRequestDto){
        MyOrder myOrder = myOrderService.createDirectOrder(myOrderRequestDto,username);
        return myOrder;
    }

    @GetMapping("/api/MyOrder/{username}")
    public List<MyOrder> getMyOrder(@PathVariable String username){
        return myOrderService.getMyOrder(username);
    }
}
