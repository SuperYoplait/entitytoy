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
        List<CartItem> select_cart = cartItemRepository.findByCartId(userid); //카트 아이템
        Optional<Item> select_item = itemRepository.findById(id); //아이템
        Optional<Cart> select_user = cartRepository.findById(userid);
        Cart user_cart  = select_user.get(); //카트에 담은 합산 값 계산 하기 위함
        Item insert_item = select_item.get();// 아이템 밀어넣기 위함
        CartItem saveItem = new CartItem();
        

        if(user_cart.getId() == null || insert_item.getId() == null){
            return "item-error";
        }

        if(saveItem.getId() == null){
            saveItem.setId(null);
            saveItem.setCnt(i_count);
            saveItem.setCart(user_cart);
            saveItem.setItem(insert_item);
        }
        

        System.out.println("\n\n"+saveItem);
        cartItemRepository.save(saveItem);


        
        //Cart_Item newCart_item = new Cart_Item(3000L, 1L, insert_item, call_cart);
        //System.out.println("\n\n"+newCart_item);
        //Cart_Item save_Cart_Item = cart_itemRepository.save(newCart_item);
        //System.out.println("\n\n"+save_Cart_Item);

        //System.out.println("\n\n" + cart_itemRepository.save(newCart_item));
        //System.out.println("\n\n" + cartRepository.findAll());

        return "item-insert";
    }

}