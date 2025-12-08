package ua.lpnu.domain.flower;

import ua.lpnu.domain.Color;

public class Tulip extends Flower {
    private final boolean isClosed;

    public Tulip(double price, Color color, FreshnessLevel freshness, double stemLength, boolean isClosed) {
        super(price, "Tulip", stemLength, color, freshness);
        this.isClosed = isClosed;
    }

    public boolean isClosed() {
        return isClosed;
    }

    @Override
    public String toString() {
        return super.toString() + (this.isClosed ? " Tulip is closed" : "Tulip is open");
    }
}
