package com.mainacademy.dao;

import com.mainacademy.model.Item;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemDAOTest {

    private static List<Item> items = new ArrayList<>();


    @BeforeAll
    static void setUp() {
        Item item = new Item("test_item_code", "test_name", new Integer(123456));
        items.add(item);
    }

    @AfterAll
    static void tearDown() {
        for(Item item: items) {
            if (item.getId() != null) {
                ItemDAO.delete(item.getId());
            }
        }
    }

    @Test
    void creatAndFindAndDelete() {
        assertNull(items.get(0).getId());
        Item itemInDB = ItemDAO.addItem(items.get(0));

        assertNotNull(itemInDB);
        assertNotNull(itemInDB.getId());

        Item checkedItemInDB = ItemDAO.findById(itemInDB.getId());
        assertNotNull(checkedItemInDB);

        ItemDAO.delete(checkedItemInDB.getId());
        Item deletedItem = ItemDAO.findById(itemInDB.getId());
        assertNull(deletedItem);

    }
}