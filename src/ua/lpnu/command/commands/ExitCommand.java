package ua.lpnu.command.commands;

import ua.lpnu.manager.FlowerShopManager;

public class ExitCommand implements ICommand {
    FlowerShopManager manager;

    public ExitCommand(FlowerShopManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.exit();
    }
}
