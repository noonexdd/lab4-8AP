package ua.lpnu.domain;

import ua.lpnu.domain.bouquet.IBouquetItem;

public record Accessory(String name, double price) implements IBouquetItem {

    @Override
    public String toString() {
        return String.format("Accessory: %s, Price: %.2f", this.name, this.price);
    }
}
