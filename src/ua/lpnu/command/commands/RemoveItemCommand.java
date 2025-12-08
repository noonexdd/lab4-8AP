package ua.lpnu.command.commands;

import ua.lpnu.manager.FlowerShopManager;

public class RemoveItemCommand implements ICommand {
    FlowerShopManager manager;

    public RemoveItemCommand(FlowerShopManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.removeItemFromBouquet();
    }
}
