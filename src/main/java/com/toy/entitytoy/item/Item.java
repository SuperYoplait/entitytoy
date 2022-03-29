package com.toy.entitytoy.item;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "ITEM")
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue()
    private Long id; //pk

    private String name; //item name

    private Long price; //item price

    private String option; //item option ex) 옷 -> S, M, L, XL....
}
