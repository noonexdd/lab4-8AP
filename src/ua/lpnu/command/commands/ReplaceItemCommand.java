package ua.lpnu.command.commands;

import ua.lpnu.manager.FlowerShopManager;

public class ReplaceItemCommand implements ICommand {
    FlowerShopManager manager;

    public ReplaceItemCommand(FlowerShopManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.replaceItem();
    }
}
