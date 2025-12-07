package ua.lpnu.command.commands;

import ua.lpnu.manager.FlowerShopManager;

public class SaveBouquetCommand implements ICommand {
    FlowerShopManager manager;

    public SaveBouquetCommand(FlowerShopManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.saveBouquetToFile();
    }
}
