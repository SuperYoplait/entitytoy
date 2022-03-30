package com.toy.entitytoy.cartitem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.toy.entitytoy.cart.Cart;
import com.toy.entitytoy.item.Item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "CARTITEM")
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long cnt;

    @OneToOne //상품에 대한 1:1 맵핑
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    private Cart cart;

}
