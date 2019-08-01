package com.mainacademy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Item {
    private Integer id;
    private String itemCode;
    private String name;
    private Integer price;

    public Item(String itemCode, String name, Integer price) {
        this.itemCode = itemCode;
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(itemCode, item.itemCode) &&
                Objects.equals(name, item.name) &&
                Objects.equals(price, item.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemCode, name, price);
    }
}
