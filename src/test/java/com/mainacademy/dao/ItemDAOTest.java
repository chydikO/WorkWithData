package com.mainacademy.dao;

import com.mainacademy.model.Item;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemDAOTest {

    private static List<Item> items = new ArrayList<>();
    private static final String NAME = "test_name";

    @BeforeAll
    static void setUp() {
        Item item = new Item("test_item_code", NAME, new Integer(123456));
        items.add(item);
    }

    @AfterAll
    static void tearDown() {
        for (Item item: items) {
            if (item.getId() != null) {
                UserDAO.delete(item.getId());
            }
        }
    }

    @Test
    void findName() {
        Item itemDublicat = new Item("test_item_code_dublicat", NAME, new Integer(7890123));
        items.add(itemDublicat);

        int position = 0;
        for (Item itemInDB: items) {
            itemInDB = ItemDAO.addItem(items.get(position++));
            assertNotNull(itemInDB);
        }

        List<Item> checkedItemsInDB = ItemDAO.findName(NAME);

        assertArrayEquals( items.toArray(), checkedItemsInDB.toArray());
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



    @Test
    void update() {
        Item itemUpdate = new Item("test_item_code_update", NAME, new Integer(987654321));

        Item itemFromBDForUpdate = ItemDAO.addItem(items.get(0));
        itemUpdate.setId(itemFromBDForUpdate.getId());
        items.add(itemUpdate);
        assertNotNull(itemFromBDForUpdate);

        Item resultAfterUpdate = ItemDAO.update(itemUpdate);
        assertEquals(itemUpdate, resultAfterUpdate);
    }

    @Test
    void delete() {
        Item itemFromBDForUpdate = ItemDAO.addItem(items.get(0));
        assertNotNull(itemFromBDForUpdate);

        ItemDAO.delete(itemFromBDForUpdate.getId());
        assertNull(ItemDAO.findById(itemFromBDForUpdate.getId()));
        System.out.println();
    }
}