package ua.lpnu.command.invoker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.lpnu.command.commands.ICommand;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private final Map<String, ICommand> menuItems = new LinkedHashMap<>();
    private final Scanner input = new Scanner(System.in);

    private static final Logger logger = LogManager.getLogger(Menu.class);

    public void start() {
        logger.info("Menu started. Waiting for user input.");
        while (true) {
            System.out.println("Menu");
            String[] commandNames = menuItems.keySet().toArray(new String[0]);

            int i = 1;
            for (String commandName : commandNames) {
                System.out.printf("%d.%s: \n", i++, commandName);
            }

            System.out.println("Your choice: ");
            int choice = getUserChoice();

            if (choice >= 0 && choice < commandNames.length) {
                ICommand command = menuItems.get(commandNames[choice]);
                logger.info("Executing command: '{}'", commandNames[choice]);
                command.execute();
            } else {
                logger.warn("User selected invalid menu index: {}", choice + 1);
                System.out.println("Invalid choice.");
            }
        }
    }

    public void setCommand(String name, ICommand command) {
        menuItems.put(name, command);
        logger.debug("Registered command: '{}'", name);
    }

    private int getUserChoice() {
        try {
            return Integer.parseInt(input.nextLine()) - 1;
        } catch (NumberFormatException e) {
            logger.error("Invalid input format: User entered non-integer value.", e);
            return -1;
        }
    }
}
