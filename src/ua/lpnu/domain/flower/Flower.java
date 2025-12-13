package ua.lpnu.domain.flower;

import ua.lpnu.domain.Color;
import ua.lpnu.domain.bouquet.IBouquetItem;

public abstract class Flower implements IBouquetItem {
    protected double price;
    protected String name;
    protected double stemLength;
    protected Color color;
    protected FreshnessLevel freshness;

    protected Flower(double price, String name, double stemLength, Color color, FreshnessLevel freshness) {
        this.price = price;
        this.name = name;
        this.stemLength = stemLength;
        this.color = color;
        this.freshness = freshness;
    }

    @Override
    public double price() {
        return price;
    }

    @Override
    public String name() {
        return name;
    }

    public double getStemLength() {
        return stemLength;
    }

    public Color getColor() {
        return color;
    }

    public FreshnessLevel getFreshness() {
        return freshness;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Price: %f.2, Color: %s, Freshness: %s, Stem Length: %f",
                this.name, this.price, this.color, this.freshness, this.stemLength);

    }
}
