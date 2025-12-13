package test.ua.lpnu.domain.bouquet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ua.lpnu.domain.*;
import ua.lpnu.domain.bouquet.Bouquet;
import ua.lpnu.domain.bouquet.IBouquetItem;
import ua.lpnu.domain.flower.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BouquetTest {
    private Bouquet bouquet;

    @BeforeEach
    void setUp() {
        bouquet = new Bouquet();
    }

    @Test
    @DisplayName("Calculate total price correctly")
    void calculatePriceTest() {
        bouquet.addItem(new Rose(50.0, true, Color.RED, FreshnessLevel.FRESH, 40.0));
        bouquet.addItem(new Accessory("Ribbon", 10.0));

        double price = bouquet.calculatePrice();

        assertEquals(60.0, price, 0.001);
    }

    @Test
    @DisplayName("Sort flowers by freshness")
    void sortFlowerTest() {
        Rose oldRose = new Rose(10, true, Color.RED, FreshnessLevel.OLD, 10);
        Tulip freshTulip = new Tulip(10, Color.YELLOW, FreshnessLevel.FRESH, 10, false);
        Accessory accessory = new Accessory("Paper", 5);

        bouquet.addItem(accessory);
        bouquet.addItem(oldRose);
        bouquet.addItem(freshTulip);

        bouquet.sortFlowerByFreshness();
        List<IBouquetItem> items = bouquet.getItems();

        assertEquals(freshTulip, items.get(0));
        assertEquals(oldRose, items.get(1));
        assertEquals(accessory, items.get(2));
    }

    @Test
    @DisplayName("Find flower by stem length")
    void findFlowerByStemLengthTest() {
        Flower shortFlower = new Rose(10, true, Color.RED, FreshnessLevel.FRESH, 10.0);
        Flower targetFlower = new Rose(10, true, Color.RED, FreshnessLevel.FRESH, 50.0);
        Flower longFlower = new Rose(10, true, Color.RED, FreshnessLevel.FRESH, 100.0);

        bouquet.addItem(shortFlower);
        bouquet.addItem(targetFlower);
        bouquet.addItem(longFlower);

        List<IBouquetItem> result = bouquet.findFlowerByStemLength(40, 60);

        assertEquals(1, result.size());
        assertEquals(targetFlower, result.getFirst());
    }

    @Test
    @DisplayName("Add item should increase size and store the item")
    void addItemTest() {
        IBouquetItem item = new Rose(50, true, Color.RED, FreshnessLevel.FRESH, 40);

        bouquet.addItem(item);

        assertEquals(1, bouquet.size());
        assertEquals(item, bouquet.getItem(0));
    }

    @Test
    @DisplayName("Remove item should decrease size and delete correct item")
    void removeItemTest() {
        Accessory item1 = new Accessory("Ribbon", 10);
        Accessory item2 = new Accessory("Paper", 5);
        bouquet.addItem(item1);
        bouquet.addItem(item2);

        bouquet.removeItem(0);

        assertEquals(1, bouquet.size());
        assertEquals(item2, bouquet.getItem(0));
    }

    @Test
    @DisplayName("GetItems should return a safe copy of the list")
    void getItemsDefensiveCopyTest() {
        bouquet.addItem(new Accessory("Ribbon", 10));

        List<IBouquetItem> items = bouquet.getItems();
        items.clear();

        assertEquals(1, bouquet.size());
    }

    @Test
    @DisplayName("GetItem should handle invalid indexes safely")
    void getItemInvalidIndexTest() {
        bouquet.addItem(new Accessory("Ribbon", 10));

        assertNull(bouquet.getItem(100));
        assertNull(bouquet.getItem(-1));
    }

    @Test
    @DisplayName("ToString should contain key information")
    void toStringTest() {
        bouquet.addItem(new Accessory("Ribbon", 10.50));

        String result = bouquet.toString();

        assertNotNull(result);
        assertTrue(result.contains("Ribbon"));
        assertTrue(result.contains("10,50"));
    }
}
