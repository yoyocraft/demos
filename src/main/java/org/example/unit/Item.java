package org.example.unit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Item {
    /**
     * 商品单价
     */
    private double price;

    /**
     * 商品数量
     */
    private int quantity;
}
