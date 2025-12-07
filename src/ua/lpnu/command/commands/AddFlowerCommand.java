package ua.lpnu.command.commands;

import ua.lpnu.manager.FlowerShopManager;

public class AddFlowerCommand implements ICommand {
    FlowerShopManager manager;

    public AddFlowerCommand(FlowerShopManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.addFlowerToBouquet();
    }
}
