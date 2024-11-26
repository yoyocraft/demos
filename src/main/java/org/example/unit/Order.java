package org.example.unit;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Order {
    /**
     * 商品列表
     */
    private List<Item> items;

    /**
     * 折扣策略
     */
    private Discount discount;
}
