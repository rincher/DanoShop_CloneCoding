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

    // Cart에서 주문내역으로 넘겨주는 서비스.
    //CART에 저장되어 있는 것들을 가져와서 MyOrder에 저장하기 위해서 MyOrder에 @Setter를 해주어야 한다.
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
    //바로 주문하기 서비스
    @Transactional
    public MyOrder createDirectOrder(MyOrderRequestDto myOrderRequestDto, String username){
        MyOrder myOrder = new MyOrder(myOrderRequestDto, username);
        myOrderRepository.save(myOrder);
        return myOrder;
    }

    public List<MyOrder> getMyOrder(String username){return myOrderRepository.findAllByUsernameOrderByCreatedAtDesc(username);}
}
