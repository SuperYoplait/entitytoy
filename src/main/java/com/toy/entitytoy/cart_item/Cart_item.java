package com.toy.entitytoy.cart_item;

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
@Table(name = "CART_ITEM")
@AllArgsConstructor
@NoArgsConstructor
public class Cart_item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long item_count;

    @OneToOne //상품에 대한 1:1 맵핑
    @JoinColumn(name = "item_id")
    private Item item;

    // @ManyToOne
    // @JoinColumn(name = "cart_id")
    // private Long cart_id;

}
