package test.ua.lpnu.domain.flower;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ua.lpnu.domain.Color;
import ua.lpnu.domain.flower.Flower;
import ua.lpnu.domain.flower.FreshnessLevel;

import static org.junit.jupiter.api.Assertions.*;

public class FlowerTest {
    private static class ConcreteFlower extends Flower {
        public ConcreteFlower(double price, String name, double stemLength, Color color, FreshnessLevel freshness) {
            super(price, name, stemLength, color, freshness);
        }
    }

    @Test
    @DisplayName("Constructor should set all fields correctly")
    void constructorAndGettersTest() {
        double price = 150.0;
        String name = "Test Flower";
        double stemLength = 55.5;
        Color color = Color.RED;
        FreshnessLevel freshness = FreshnessLevel.FRESH;

        Flower flower = new ConcreteFlower(price, name, stemLength, color, freshness);

        assertEquals(price, flower.price());
        assertEquals(name, flower.name());
        assertEquals(stemLength, flower.getStemLength());
        assertEquals(color, flower.getColor());
        assertEquals(freshness, flower.getFreshness());
    }

    @Test
    @DisplayName("toString should contain all flower details")
    void toStringTest() {
        Flower flower = new ConcreteFlower(100.0, "TestFlower", 50.0, Color.WHITE, FreshnessLevel.OLD);

        String result = flower.toString();

        assertNotNull(result);
        assertTrue(result.contains("TestFlower"));
        assertTrue(result.contains("WHITE"));
        assertTrue(result.contains("OLD"));
        assertTrue(result.contains("100.0"));
    }
}
