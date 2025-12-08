package ua.lpnu.command.commands;

import ua.lpnu.manager.FlowerShopManager;

public class LoadBouquetCommand implements ICommand {
    FlowerShopManager manager;

    public LoadBouquetCommand(FlowerShopManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.loadBouquetFromFile();
    }
}
