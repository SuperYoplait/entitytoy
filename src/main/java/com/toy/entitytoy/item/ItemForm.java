package com.toy.entitytoy.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemForm {
    private Long id;

    private String name;

    private Long price;

    private String option;
}
