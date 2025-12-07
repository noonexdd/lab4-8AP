package ua.lpnu.command.commands;

import ua.lpnu.manager.FlowerShopManager;

public class ShowCatalogCommand implements ICommand {
    FlowerShopManager manager;

    public ShowCatalogCommand(FlowerShopManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.showCatalog();
    }
}
