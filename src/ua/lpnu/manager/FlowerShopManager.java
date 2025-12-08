package ua.lpnu.manager;

import ua.lpnu.domain.bouquet.Bouquet;
import ua.lpnu.domain.FlowerCatalog;
import ua.lpnu.storage.FileStorage;
import ua.lpnu.domain.bouquet.IBouquetItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FlowerShopManager {
    private final Map<String, Bouquet> bouquets = new HashMap<>();
    private Bouquet currentBouquet;
    private final FlowerCatalog catalog = new FlowerCatalog();
    private FileStorage fileStorage;
    private final Scanner input = new Scanner(System.in);


    public void createNewBouquet() {
        System.out.println("Creating bouquet...");
        System.out.println("Enter bouquet name: ");
        String bouquetName = input.nextLine().trim();

        if (bouquets.containsKey(bouquetName)) {
            System.out.println("Bouquet already exists!");
            return;
        }

        Bouquet bouquet = new Bouquet();
        bouquets.put(bouquetName, bouquet);
        this.currentBouquet = bouquet;

        System.out.printf("Bouquet %s created!", bouquetName);
    }

    public void addItemToBouquet() {
        System.out.println("Adding item to the bouquet...");
        showCatalog();
        System.out.println("Select the item you want to add to the bouquet");

        int choice = getUserChoice();

        IBouquetItem selectedItem = catalog.getItem(choice);
        if (selectedItem != null) {
            currentBouquet.addItem(selectedItem);
            System.out.printf("The item %s has been successfully added to the bouquet.", selectedItem.name());
        } else {
            System.out.println("There is no such item in the catalog");
        }
    }

    public void sortCurrentBouquet() {
        System.out.println("Sorting bouquets by flower freshness...");
        currentBouquet.sortFlowerByFreshness();
        System.out.println("Assorted bouquet: ");
        System.out.println(currentBouquet);
    }

    public void findInBouquet() {
        System.out.println("Finding flowers in a bouquet within the stem length range...");

        try {
            System.out.println("Enter the minimum stem length: ");
            double min = Double.parseDouble(input.nextLine());

            System.out.println("Enter the maximum stem length: ");
            double max = Double.parseDouble(input.nextLine());

            List<IBouquetItem> foundFlowers = currentBouquet.findFlowerByStemLength(min, max);

            if (foundFlowers.isEmpty()) {
                System.out.println("There are no flowers with this stem length");
            } else {
                System.out.println("Found flowers: ");
                foundFlowers.forEach(System.out::println);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
        }
    }

    public void showBouquetInfo() {
        System.out.println(currentBouquet);
    }

    public void saveBouquetToFile() {

    }

    public void removeItemFromBouquet() {
        System.out.println("Removing a flower from a bouquet...");
        if (currentBouquet.size() == 0) {
            System.out.println("Bouquet is empty.");
            return;
        }

        for (int i = 0; i < currentBouquet.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, currentBouquet.getItem(i));
        }

        System.out.println("Select a item to remove from the bouquet");
        int choice = getUserChoice();

        if (choice >= 0 && choice < currentBouquet.size()) {
            IBouquetItem selectedItem = currentBouquet.getItem(choice);
            currentBouquet.removeItem(choice);
            System.out.printf("The flower has been successfully removed: %s\n", selectedItem.name());
        } else {
            System.out.println("Incorrect number");
        }
    }

    public void showGuide() {
        System.out.println("""
                            Guide:
                Welcome to the Flower Shop Manager!
                Here is how to use the application:
                
                1. create bouquet
                Starts a new empty bouquet.
                
                2. add / remove / replace
                - You will see a numbered list.
                - Enter the number of the item to select it.
                
                3. find flowers (Stem Length)
                Allows you to filter flowers by stem length.
                - Input format: two decimal numbers, each separately
                - Example: “20.3” and “40.9” will find flowers
                with stems ranging from 20.3 to 40.9 cm in length.
                
                4. sort bouquet
                Automatically sorts the bouquet contents:
                Fresh flowers -> Top priority.
                - Old flowers -> Lower priority.
                - Accessories -> Always at the bottom.
                
                5. save to file
                Saves the current bouquet to 'bouquet.txt' (or similar).
                """);
    }

    public void showCatalog() {
        System.out.println(catalog);
    }

    public void exit() {
        System.exit(0);
    }

    public void replaceItem() {
        System.out.println("Replacing bouquet...");

        for (int i = 0; i < currentBouquet.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, currentBouquet.getItem(i));
        }
        System.out.println("Select the item you want to replace");
        int indexToRemove = getUserChoice();

        if (indexToRemove < 0 || indexToRemove >= currentBouquet.size()) {
            System.out.println("Incorrect number");
            return;
        }
        IBouquetItem oldItem = currentBouquet.getItem(indexToRemove);

        System.out.println("Select the item from the catalog that you want to replace.");
        showCatalog();

        int indexToAdd = getUserChoice();

        IBouquetItem newItem = catalog.getItem(indexToAdd);
        if (newItem == null) {
            System.out.println("There is no such item in the catalog");
            return;
        }

        currentBouquet.removeItem(indexToRemove);
        currentBouquet.addItem(newItem);
        System.out.printf("The subject %s was successfully replaced with %s\n", oldItem.name(), newItem.name());
    }

    public void switchBouquet() {
        System.out.println("Switching bouquet...");
        if (bouquets.isEmpty()) {
            System.out.println("No bouquets created yet");
            return;
        }

        System.out.println("Available bouquets:");
        bouquets.forEach((bouquetName, bouquet) -> {
            String mark = (bouquet == currentBouquet) ? "Current" : "";
            System.out.printf("- %s %s", bouquetName, mark);
        });

        System.out.println("Enter the name of the bouquet you want to use:");
        String bouquetName = input.nextLine().trim();

        if (bouquets.containsKey(bouquetName)) {
            this.currentBouquet = bouquets.get(bouquetName);
            System.out.printf("Bouquet changed to %s", bouquetName);
        } else {
            System.out.println("Bouquet not found");
        }
    }

    private int getUserChoice() {
        try {
            return Integer.parseInt(input.nextLine()) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

}
