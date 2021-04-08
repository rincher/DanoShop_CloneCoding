package com.example.demo.controller;


import com.example.demo.Dto.MyOrderRequestDto;
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

    //쇼핑카트에서 내 주문이력으로 넘길때
    @PostMapping("/api/MyOrder/{username}")
    public MyOrder createMyOrder(@PathVariable String username){
        MyOrder myOrder = myOrderService.createMyOrder(username);
        return myOrder;
    }

    //제품 상세 페이지에서 바로 주문하기를 눌렀을때
    @PostMapping("/api/DirectOrder/{username}")
    public MyOrder createDirectOrder(@PathVariable String username, @RequestBody MyOrderRequestDto myOrderRequestDto){
        MyOrder myOrder = myOrderService.createDirectOrder(myOrderRequestDto,username);
        return myOrder;
    }

    //각 사용자 별 주문내역
    @GetMapping("/api/MyOrder/{username}")
    public List<MyOrder> getMyOrder(@PathVariable String username){
        return myOrderService.getMyOrder(username);
    }
}
