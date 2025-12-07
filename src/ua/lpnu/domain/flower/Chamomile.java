package ua.lpnu.domain.flower;

import ua.lpnu.domain.Color;

public class Chamomile extends Flower {
    public Chamomile(double price, Color color, FreshnessLevel freshness, double stemLength) {
        super(price, "Chamomile", stemLength, color, freshness);

    }
}
