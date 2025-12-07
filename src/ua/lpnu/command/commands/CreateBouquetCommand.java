package ua.lpnu.command.commands;

import ua.lpnu.manager.FlowerShopManager;

public class CreateBouquetCommand implements ICommand {
    FlowerShopManager manager;

    public CreateBouquetCommand(FlowerShopManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.createNewBouquet();
    }
}
