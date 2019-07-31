package com.mainacademy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Item {
    private Integer id;
    private Integer itemId;
    private Integer amount;
    private Integer cardId;

    public Item(Integer itemId, Integer amount, Integer cardId) {
        this.itemId = itemId;
        this.amount = amount;
        this.cardId = cardId;
    }
}
