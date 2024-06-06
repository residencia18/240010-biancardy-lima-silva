package com.example.atividades.atividade09;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class ItemCollectionTest {

    private ItemCollection itemCollection;

    @Before
    public void setUp() {
        itemCollection = new ItemCollection();
    }

    @Test
    public void testAddItem() {
        Item item = new Item("Item1");
        itemCollection.addItem(item);
        List<Item> items = itemCollection.getItems();
        assertEquals(1, items.size());
        assertTrue(items.contains(item));
    }

    @Test
    public void testAddNullItem() {
        assertThrows(IllegalArgumentException.class, () -> itemCollection.addItem(null));
    }

    @Test
    public void testRemoveItem() {
        Item item = new Item("Item1");
        itemCollection.addItem(item);
        itemCollection.removeItem(item);
        List<Item> items = itemCollection.getItems();
        assertEquals(0, items.size());
        assertTrue(!items.contains(item));
    }

    @Test
    public void testRemoveItemNotInCollection() {
        Item item = new Item("Item1");
        assertThrows(IllegalArgumentException.class, () -> itemCollection.removeItem(item));
    }

    @Test
    public void testGetItems() {
        Item item1 = new Item("Item1");
        Item item2 = new Item("Item2");
        itemCollection.addItem(item1);
        itemCollection.addItem(item2);
        List<Item> items = itemCollection.getItems();
        assertEquals(2, items.size());
        assertTrue(items.contains(item1));
        assertTrue(items.contains(item2));
    }
}
