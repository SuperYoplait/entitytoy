package com.toy.entitytoy.cart;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartRepository cartRepository;

    private final Long id = 3080L;
    @GetMapping("/my-cart")
    public String my_cart(Model model){
        Optional<Cart> myCart = cartRepository.findById(id);
        Cart mycart_get = myCart.get();
        if(mycart_get.getSum_price() == 0){
            System.out.println("비었음");
        }else{
            System.out.println("\n아이템 목록 : "+mycart_get.getCart_item());
            System.out.println("\n전체 상품 가격 : " + mycart_get.getSum_price());
        }
        return "my-cart";
    }
}
