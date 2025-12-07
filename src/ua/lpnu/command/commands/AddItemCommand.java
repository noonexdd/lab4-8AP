package ua.lpnu.command.commands;

import ua.lpnu.manager.FlowerShopManager;

public class AddItemCommand implements ICommand {
    FlowerShopManager manager;

    public AddItemCommand(FlowerShopManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.addItemToBouquet();
    }
}
