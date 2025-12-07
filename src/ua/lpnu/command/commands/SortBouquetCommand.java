package ua.lpnu.command.commands;

import ua.lpnu.manager.FlowerShopManager;

public class SortBouquetCommand implements ICommand {
    FlowerShopManager manager;

    public SortBouquetCommand(FlowerShopManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.sortCurrentBouquet();
    }
}
