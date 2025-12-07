package ua.lpnu.main;

import ua.lpnu.command.commands.*;
import ua.lpnu.manager.FlowerShopManager;
import ua.lpnu.command.invoker.Menu;

public class Main {
    public static void main(String[] args) {
        FlowerShopManager manager = new FlowerShopManager();
        Menu menu = new Menu();

        menu.setCommand("Create new bouquet", new CreateBouquetCommand(manager));
        menu.setCommand("Add flower", new AddFlowerCommand(manager));
        menu.setCommand("Find a flower in a bouquet", new FindFlowerCommand(manager));
        menu.setCommand("Replace flower", new ReplaceFlowerCommand(manager));
        menu.setCommand("Remove flower", new RemoveFlowerCommand(manager));
        menu.setCommand("Sort bouquet", new SortBouquetCommand(manager));
        menu.setCommand("View bouquet", new ViewBouquetCommand(manager));
        menu.setCommand("Save bouquet to file", new SaveBouquetCommand(manager));
        menu.setCommand("Show catalog", new ShowCatalogCommand(manager));
        menu.setCommand("Get guide", new GetGuideCommand(manager));
        menu.setCommand("Exit", new ExitCommand(manager));

        menu.start();
    }
}
