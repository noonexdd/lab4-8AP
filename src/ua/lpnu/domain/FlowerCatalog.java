package ua.lpnu.domain;

import ua.lpnu.domain.bouquet.IBouquetItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FlowerCatalog {
    private final List<IBouquetItem> items = new ArrayList<>();

    public void addItem(IBouquetItem item) {
        items.add(item);
    }

    public IBouquetItem getItem(int index) {
        if (index >= 0 && index < items.size())
            return items.get(index);
        return null;
    }

    @Override
    public String toString() {
        if (items.isEmpty()) {
            return "Catalog is empty";
        }

        return IntStream.range(0, items.size()).mapToObj(i -> (i + 1) + "." + items.get(i).toString())
                .collect(Collectors.joining("\n"));
    }
}
