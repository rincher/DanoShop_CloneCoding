package com.example.demo.service;

import com.example.demo.Dto.MyOrderRequestDto;
import com.example.demo.domain.Cart;
import com.example.demo.domain.MyOrder;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.MyOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MyOrderService {
    private final MyOrderRepository myOrderRepository;
    private final CartRepository cartRepository;
    @Transactional
    public MyOrder createMyOrder(String username){
        List<Cart> allData = cartRepository.findAllByUsername(username);
        for (Cart cart : allData){
            MyOrder myOrder = new MyOrder();
            myOrder.setId(cart.getId());
            myOrder.setAmount(cart.getAmount());
            myOrder.setImg_url(cart.getImg_url());
            myOrder.setPrice(cart.getPrice());
            myOrder.setUsername(cart.getUsername());
            myOrder.setProduct_name(cart.getProduct_name());
            myOrderRepository.save(myOrder);
            cartRepository.deleteAllByUsername(username);
        }
        return null;
    }

    @Transactional
    public MyOrder createDirectOrder(MyOrderRequestDto myOrderRequestDto, String username){
        MyOrder myOrder = new MyOrder(myOrderRequestDto, username);
        myOrderRepository.save(myOrder);
        return myOrder;
    }

    public List<MyOrder> getMyOrder(String username){return myOrderRepository.findAllByUsernameOrderByCreatedAtDesc(username);}
}
