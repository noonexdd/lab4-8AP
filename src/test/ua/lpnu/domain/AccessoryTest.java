package test.ua.lpnu.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ua.lpnu.domain.Accessory;

import static org.junit.jupiter.api.Assertions.*;

public class AccessoryTest {
    @Test
    @DisplayName("Should create record and retrieve correct values")
    void constructorAndGettersTest() {
        String name = "Golden Ribbon";
        double price = 25.50;

        Accessory accessory = new Accessory(name, price);

        assertEquals(name, accessory.name());
        assertEquals(price, accessory.price());
    }

    @Test
    @DisplayName("ToString should use custom format")
    void toStringTest() {
        Accessory accessory = new Accessory("Vase", 100.00);

        String result = accessory.toString();

        assertNotNull(result);
        assertTrue(result.contains("Accessory: Vase"));
        assertTrue(result.contains("Price:"));
        assertTrue(result.contains("100.00"));
    }
}
