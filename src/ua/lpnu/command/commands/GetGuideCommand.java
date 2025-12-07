package ua.lpnu.command.commands;

import ua.lpnu.manager.FlowerShopManager;

public class GetGuideCommand implements ICommand {
    FlowerShopManager manager;

    public GetGuideCommand(FlowerShopManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.showGuide();
    }
}
