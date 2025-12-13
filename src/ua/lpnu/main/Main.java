package ua.lpnu.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.lpnu.command.commands.*;
import ua.lpnu.manager.FlowerShopManager;
import ua.lpnu.command.invoker.Menu;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    static void main(String[] args) {
        logger.info("Application is starting");

        try {
            logger.debug("Initializing FlowerShopManager and Menu components.");
            FlowerShopManager manager = new FlowerShopManager();
            Menu menu = new Menu();

            menu.setCommand("Create new bouquet", new CreateBouquetCommand(manager));
            menu.setCommand("Add item", new AddItemCommand(manager));
            menu.setCommand("Find a flower in a bouquet", new FindFlowerCommand(manager));
            menu.setCommand("Replace item", new ReplaceItemCommand(manager));
            menu.setCommand("Remove item", new RemoveItemCommand(manager));
            menu.setCommand("Sort bouquet", new SortBouquetCommand(manager));
            menu.setCommand("View bouquet", new ViewBouquetCommand(manager));
            menu.setCommand("Save bouquet to file", new SaveBouquetCommand(manager));
            menu.setCommand("Load bouquet from file", new LoadBouquetCommand(manager));
            menu.setCommand("Show catalog", new ShowCatalogCommand(manager));
            menu.setCommand("Switch bouquet", new SwitchBouquetCommand(manager));
            menu.setCommand("Get guide", new GetGuideCommand(manager));
            menu.setCommand("Exit", new ExitCommand(manager));

            logger.info("Initialization complete. Starting the main menu loop.");
            menu.start();

            logger.info("Application finished work successfully.");
        } catch (Exception e) {
            logger.fatal("The application crashed unexpectedly.", e);
        }
    }
}
