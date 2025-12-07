package ua.lpnu.domain.bouquet;

import ua.lpnu.domain.flower.Flower;

import java.util.ArrayList;
import java.util.List;

public class Bouquet {
    private final List<IBouquetItem> items = new ArrayList<>();

    public void addItem(IBouquetItem item) {
        items.add(item);
    }

    public double calculatePrice() {
        return items.stream().mapToDouble(IBouquetItem::price).sum();
    }

    public void sortFlowerByFreshness() {
        List<IBouquetItem> sortedItem = items.stream().sorted((item1, item2) -> {
                    if (item1 instanceof Flower f1 && item2 instanceof Flower f2) {
                        return f1.getFreshness().compareTo(f2.getFreshness());
                    }

                    if (item1 instanceof Flower) return -1;

                    if (item2 instanceof Flower) return 1;

                    return 0;
                })
                .toList();

        items.clear();
        items.addAll(sortedItem);
    }

    public List<IBouquetItem> findFlowerByStemLength(double min, double max) {
        return items.stream().filter(item -> item instanceof Flower f &&
                f.getStemLength() >= min && f.getStemLength() <= max).toList();
    }

    @Override
    public String toString() {
        if (items.isEmpty()) {
            return "Bouquet is empty";
        }

        StringBuilder sb = new StringBuilder("Bouquet contains:\n");
        for (IBouquetItem item : items) {
            sb.append(item.toString()).append("\n");
        }
        sb.append(String.format("Total price: %.2f\n", calculatePrice()));
        return sb.toString();
    }

    public int size() {
        return items.size();
    }

    public IBouquetItem getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        }
    }
}
