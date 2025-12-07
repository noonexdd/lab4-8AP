package ua.lpnu.command.commands;

import ua.lpnu.manager.FlowerShopManager;

public class FindFlowerCommand implements ICommand {
    FlowerShopManager manager;

    public FindFlowerCommand(FlowerShopManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.findInBouquet();
    }
}
