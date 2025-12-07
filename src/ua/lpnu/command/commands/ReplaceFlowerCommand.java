package ua.lpnu.command.commands;

import ua.lpnu.domain.bouquet.IBouquetItem;
import ua.lpnu.manager.FlowerShopManager;

public class ReplaceFlowerCommand implements ICommand {
    FlowerShopManager manager;

    public ReplaceFlowerCommand(FlowerShopManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.replaceFlower();
    }
}
