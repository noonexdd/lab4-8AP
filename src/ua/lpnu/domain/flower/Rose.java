package ua.lpnu.domain.flower;

import ua.lpnu.domain.Color;

public class Rose extends Flower {
    private boolean hasSpikes;

    public Rose(double price, boolean hasSpikes, Color color, FreshnessLevel freshness, double stemLength) {
        super(price, "Rose", stemLength, color, freshness);
        this.hasSpikes = hasSpikes;
    }

    @Override
    public String toString() {
        return super.toString() + (this.hasSpikes ? " with spikes" : "no spikes");
    }
}
