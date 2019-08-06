package com.mainacademy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Order {
    private Integer id;
    private Integer itemId;
    private Integer amount;
    private Integer cartId;

    public Order(Integer itemCode, Integer price, Integer cartId) {
        this.itemId = itemCode;
        this.amount = price;
        this.cartId = cartId;
    }
}
