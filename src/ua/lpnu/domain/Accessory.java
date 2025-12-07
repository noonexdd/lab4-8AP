package ua.lpnu.domain;

import ua.lpnu.domain.bouquet.IBouquetItem;

public class Accessory implements IBouquetItem {
    private final String name;
    private final double price;

    public Accessory(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("Accessory: %s, Price: %.2f", this.name, this.price);
    }
}
