package com.toy.entitytoy.item;

import java.util.List;
import java.util.Optional;

import com.toy.entitytoy.cart.Cart;
import com.toy.entitytoy.cart.CartRepository;
import com.toy.entitytoy.cartitem.CartItem;
import com.toy.entitytoy.cartitem.CartItemForm;
import com.toy.entitytoy.cartitem.CartItemRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    private final Long userid = 3080L;

    @GetMapping("/")
    public String item_list(Model model) {
        List<Item> temp = itemRepository.findAll();
        System.out.println(temp);
        model.addAttribute("itemlist", itemRepository.findAll());
        return "itemlist";
    }

    @GetMapping("/item-insert/{itemId}")
    public String item_insert(Model model, @PathVariable("itemId") Long id) {
        Long i_count = 1L;
        List<CartItem> select_cart = cartItemRepository.findByCartId(userid); // 카트 아이템
        Optional<Item> select_item = itemRepository.findById(id); // 아이템
        Optional<Cart> select_user = cartRepository.findById(userid);
        Cart user_cart = select_user.get(); // 카트에 담은 합산 값 계산 하기 위함
        Item insert_item = select_item.get();// 아이템 밀어넣기 위함
        CartItem saveItem = new CartItem();

        if (user_cart.getId() == null || insert_item.getId() == null) { // 유저 아이디 or 상품 아이디가 null이면 에러
            return "item-error";
        }
        // 상품 정보들을 상품 리스트에 넣기 위한 작업
        saveItem.setId(null);
        saveItem.setCnt(i_count);
        saveItem.setCart(user_cart);
        saveItem.setItem(insert_item);

        System.out.println("\n\n" + saveItem);
        cartItemRepository.save(saveItem); //장바구니 상품 리스트에 저장.

        Long sum_price_calc = user_cart.getSum_price();
        //장바구니에 담으면 현재 상품 가격도 추가해줘야 함
        sum_price_calc += saveItem.getItem().getPrice() * saveItem.getCnt();


        //추후 오버헤드 때문이라도 수정해야함
        for(CartItem get_cart_item : select_cart)
            sum_price_calc += select_item.get().getPrice() * get_cart_item.getCnt();
        System.out.println("물건 합산 금액" + sum_price_calc);
        
        user_cart.setSum_price(sum_price_calc);
        System.out.println(user_cart.getSum_price());

        cartRepository.save(user_cart); //합계 한 값 다시 저장.
        return "item-insert";
    }

}