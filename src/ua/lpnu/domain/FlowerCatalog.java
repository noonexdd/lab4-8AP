package ua.lpnu.domain;

import ua.lpnu.domain.bouquet.IBouquetItem;

import java.util.ArrayList;
import java.util.List;

public class FlowerCatalog {
    private final List<IBouquetItem> items = new ArrayList<>();

    public List<IBouquetItem> getItems() {
        return items;
    }
}
