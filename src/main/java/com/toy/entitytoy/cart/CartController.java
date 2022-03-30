package com.toy.entitytoy.cart;

import java.util.List;

import com.toy.entitytoy.cartitem.CartItem;
import com.toy.entitytoy.cartitem.CartItemRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartItemRepository cartItemRepository;

    private final Long id = 3080L;
    @GetMapping("/my-cart")
    public String my_cart(Model model){
        List<CartItem> select_cartId = cartItemRepository.findByCartId(id);
        
        
        if(select_cartId.isEmpty()){
            System.out.println("비었음");
        }else{
            for(CartItem item : select_cartId)
                System.out.println("아이템 목록 : "+ item);
            //System.out.println("\n전체 상품 가격 : " + );
        }
        return "my-cart";
    }
}
