package ua.lpnu.command.commands;

import ua.lpnu.manager.FlowerShopManager;

public class RemoveFlowerCommand implements ICommand {
    FlowerShopManager manager;

    public RemoveFlowerCommand(FlowerShopManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.removeFlowerFromBouquet();
    }
}
