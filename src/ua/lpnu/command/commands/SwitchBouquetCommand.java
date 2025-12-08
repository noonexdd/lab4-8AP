package ua.lpnu.command.commands;

import ua.lpnu.manager.FlowerShopManager;

public class SwitchBouquetCommand implements ICommand{
    FlowerShopManager manager;

    public SwitchBouquetCommand(FlowerShopManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.switchBouquet();
    }
}
