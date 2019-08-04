package com.mainacademy.service;

import com.mainacademy.dao.ItemDAO;
import com.mainacademy.model.Item;

public class ItemService {

    public static Item create(Item item) {
        return ItemDAO.addItem(item);
    }

    public static Item findById(Integer id) {
        return ItemDAO.findById(id);
    }

    //public static List<Item> findByItemId(Integer id)
}
