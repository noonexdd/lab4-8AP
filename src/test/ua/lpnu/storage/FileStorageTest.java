package test.ua.lpnu.storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ua.lpnu.domain.Accessory;
import ua.lpnu.domain.Color;
import ua.lpnu.domain.bouquet.Bouquet;
import ua.lpnu.domain.bouquet.IBouquetItem;
import ua.lpnu.domain.flower.*;
import ua.lpnu.storage.FileStorage;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileStorageTest {
    private final String testFilename = "test_bouquet_full_cycle.csv";
    private final FileStorage fileStorage = new FileStorage();

    @AfterEach
    void tearDown() {
        File file = new File(testFilename);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    @DisplayName("Should save and load ALL types of items correctly")
    void saveAndLoadFullCycleTest() {
        Bouquet originalBouquet = new Bouquet();

        Rose rose = new Rose(100.0, true, Color.RED, FreshnessLevel.FRESH, 50.5);
        Tulip tulip = new Tulip(50.0, Color.YELLOW, FreshnessLevel.MEDIUM, 30.0, true);
        Chamomile chamomile = new Chamomile(25.0, Color.WHITE, FreshnessLevel.FRESH, 20.0);
        Gypsophila gypsophila = new Gypsophila(40.0, Color.PINK, FreshnessLevel.OLD, 45.0);
        Accessory accessory = new Accessory("Silk Ribbon", 15.50);

        originalBouquet.addItem(rose);
        originalBouquet.addItem(tulip);
        originalBouquet.addItem(chamomile);
        originalBouquet.addItem(gypsophila);
        originalBouquet.addItem(accessory);

        fileStorage.saveBouquet(originalBouquet, testFilename);
        Bouquet loadedBouquet = fileStorage.loadBouquet(testFilename);

        List<IBouquetItem> items = loadedBouquet.getItems();
        assertEquals(5, items.size());

        assertInstanceOf(Rose.class, items.getFirst());
        Rose loadedRose = (Rose) items.getFirst();
        assertEquals(rose.name(), loadedRose.name());
        assertEquals(rose.price(), loadedRose.price());
        assertEquals(rose.getColor(), loadedRose.getColor());
        assertEquals(rose.getHasSpikes(), loadedRose.getHasSpikes());

        assertInstanceOf(Tulip.class, items.get(1));
        Tulip loadedTulip = (Tulip) items.get(1);
        assertEquals(tulip.name(), loadedTulip.name());
        assertEquals(tulip.isClosed(), loadedTulip.isClosed());

        assertInstanceOf(Chamomile.class, items.get(2));
        Chamomile loadedChamomile = (Chamomile) items.get(2);
        assertEquals(chamomile.getStemLength(), loadedChamomile.getStemLength());

        assertInstanceOf(Gypsophila.class, items.get(3));
        Gypsophila loadedGyp = (Gypsophila) items.get(3);
        assertEquals(gypsophila.getFreshness(), loadedGyp.getFreshness());

        assertInstanceOf(Accessory.class, items.get(4));
        Accessory loadedAccessory = (Accessory) items.get(4);
        assertEquals(accessory.name(), loadedAccessory.name());
        assertEquals(accessory.price(), loadedAccessory.price());
    }
}
