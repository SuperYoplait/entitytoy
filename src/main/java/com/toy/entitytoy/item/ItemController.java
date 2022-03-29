package com.toy.entitytoy.item;

import java.util.List;
import java.util.Optional;

import com.toy.entitytoy.cart.Cart;
import com.toy.entitytoy.cart.CartRepository;
import com.toy.entitytoy.cart_item.Cart_item;
import com.toy.entitytoy.cart_item.Cart_itemRepository;

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
    private final Cart_itemRepository cart_itemRepository;

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
        Optional<Cart> select_cart = cartRepository.findById(userid);
        Optional<Item> select_item = itemRepository.findById(id);
        Cart insert_cart = select_cart.get();
        Item insert_item = select_item.get();

//        if(select_cart.isPresent()){

//        }
        

        System.out.println("\n\n"+insert_item);
        System.out.println("\n\n"+insert_cart);
        
        //Cart_Item newCart_item = new Cart_Item(3000L, 1L, insert_item, call_cart);
        //System.out.println("\n\n"+newCart_item);
        //Cart_Item save_Cart_Item = cart_itemRepository.save(newCart_item);
        //System.out.println("\n\n"+save_Cart_Item);

        //System.out.println("\n\n" + cart_itemRepository.save(newCart_item));
        //System.out.println("\n\n" + cartRepository.findAll());

        return "item-insert";
    }

}