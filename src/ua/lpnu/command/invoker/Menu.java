package ua.lpnu.command.invoker;

import ua.lpnu.command.commands.ICommand;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private final Map<String, ICommand> menuItems = new LinkedHashMap<>();
    private final Scanner input = new Scanner(System.in);

    public void start() {
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
                command.execute();
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    public void setCommand(String name, ICommand command) {
        menuItems.put(name, command);
    }

    private int getUserChoice() {
        try {
            return Integer.parseInt(input.nextLine()) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
