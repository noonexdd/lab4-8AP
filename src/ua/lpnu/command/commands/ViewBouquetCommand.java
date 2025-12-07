package ua.lpnu.command.commands;

import ua.lpnu.manager.FlowerShopManager;

public class ViewBouquetCommand implements ICommand {
    FlowerShopManager manager;

    public ViewBouquetCommand(FlowerShopManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.showBouquetInfo();
    }
}
