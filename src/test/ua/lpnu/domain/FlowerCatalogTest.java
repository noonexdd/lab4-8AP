package test.ua.lpnu.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ua.lpnu.domain.FlowerCatalog;
import ua.lpnu.domain.bouquet.IBouquetItem;

import static org.junit.jupiter.api.Assertions.*;

public class FlowerCatalogTest {
    private FlowerCatalog catalog;

    @BeforeEach
    void setUp() {
        catalog = new FlowerCatalog();
    }

    record StubItem(String name) implements IBouquetItem {
        @Override
        public double price() {
            return 10.0;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    @Test
    @DisplayName("Should add item and retrieve it by valid index")
    void addAndGetItemTest() {
        StubItem item1 = new StubItem("Test Rose");
        StubItem item2 = new StubItem("Test Tulip");

        catalog.addItem(item1);
        catalog.addItem(item2);

        assertEquals(item1, catalog.getItem(0));
        assertEquals(item2, catalog.getItem(1));
    }

    @Test
    @DisplayName("Should return null for invalid indexes")
    void getItemInvalidIndexTest() {
        catalog.addItem(new StubItem("Flower"));

        assertNull(catalog.getItem(-1));
        assertNull(catalog.getItem(1));
        assertNull(catalog.getItem(100));
    }

    @Test
    @DisplayName("Should return numbered list when catalog is populated")
    void toStringPopulatedTest() {
        catalog.addItem(new StubItem("Rose"));
        catalog.addItem(new StubItem("Tulip"));

        String result = catalog.toString();

        assertNotNull(result);
        assertTrue(result.contains("1.Rose"));
        assertTrue(result.contains("2.Tulip"));
        assertTrue(result.contains("\n"));
    }
}
