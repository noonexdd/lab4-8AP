package ua.lpnu.manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.lpnu.domain.Accessory;
import ua.lpnu.domain.Color;
import ua.lpnu.domain.bouquet.Bouquet;
import ua.lpnu.domain.FlowerCatalog;
import ua.lpnu.domain.flower.FreshnessLevel;
import ua.lpnu.domain.flower.*;
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
    private final FileStorage fileStorage = new FileStorage();
    private final Scanner input = new Scanner(System.in);

    private static final Logger logger = LogManager.getLogger(FlowerShopManager.class);

    public FlowerShopManager() {
        logger.info("Initialization FlowerShopManager");
        fillCatalog();
    }

    public void createNewBouquet() {
        logger.info("An attempt to create a new bouquet");
        System.out.println("Creating bouquet...");
        System.out.println("Enter bouquet name: ");
        String bouquetName = input.nextLine().trim();

        if (bouquets.containsKey(bouquetName)) {
            logger.warn("A bouquet named ‘{}’ already exists", bouquetName);
            System.out.println("Bouquet already exists!");
            return;
        }

        Bouquet bouquet = new Bouquet();
        bouquets.put(bouquetName, bouquet);
        this.currentBouquet = bouquet;

        logger.info("The bouquet ‘{}’ has been successfully created and set as the current one", bouquetName);
        System.out.printf("Bouquet %s created!\n", bouquetName);
    }

    public void addItemToBouquet() {
        logger.info("Adding an item to a bouquet");
        System.out.println("Adding item to the bouquet...");
        if (isBouquetMissing()) return;

        showCatalog();
        System.out.println("Select the item you want to add to the bouquet");

        int choice = getUserChoice();
        logger.debug("The user has selected the catalog index: {}", choice + 1);

        IBouquetItem selectedItem = catalog.getItem(choice);
        if (selectedItem != null) {
            currentBouquet.addItem(selectedItem);
            logger.info("Element ‘{}’ (price: {}) added to bouquet "
                    , selectedItem.name(), selectedItem.price());
            System.out.printf("The item %s has been successfully added to the bouquet.\n", selectedItem.name());
        } else {
            logger.warn("Invalid catalog index {} when adding an item.", choice + 1);
            System.out.println("There is no such item in the catalog");
        }
    }

    public void sortCurrentBouquet() {
        logger.info("Sorting the current bouquet by freshness");
        System.out.println("Sorting bouquets by flower freshness...");
        if (isBouquetMissing()) return;

        currentBouquet.sortFlowerByFreshness();
        logger.info("The bouquet has been successfully sorted");
        System.out.println("Assorted bouquet: ");
        System.out.println(currentBouquet);
    }

    public void findInBouquet() {
        logger.info("Search for flowers by stem length");
        System.out.println("Finding flowers in a bouquet within the stem length range...");
        if (isBouquetMissing()) return;

        try {
            System.out.println("Enter the minimum stem length: ");
            double min = Double.parseDouble(input.nextLine());
            logger.debug("Minimum length entered: {}", min);

            System.out.println("Enter the maximum stem length: ");
            double max = Double.parseDouble(input.nextLine());
            logger.debug("Maximum length entered: {}", max);

            List<IBouquetItem> foundFlowers = currentBouquet.findFlowerByStemLength(min, max);

            if (foundFlowers.isEmpty()) {
                logger.info("No flowers found in the range {}-{}", min, max);
                System.out.println("There are no flowers with this stem length");
            } else {
                logger.info("{} flowers found in the range {}-{}", foundFlowers.size(), min, max);
                System.out.println("Found flowers: ");
                foundFlowers.forEach(System.out::println);
            }
        } catch (NumberFormatException e) {
            logger.error("Incorrect input (NumberFormatException) when searching for stem length", e);
            System.out.println("Invalid input");
        }
    }

    public void showBouquetInfo() {
        logger.info("Display information about the current bouquet");
        if (isBouquetMissing()) return;
        System.out.println(currentBouquet);
    }

    public void saveBouquetToFile() {
        logger.info("Saving the current bouquet to a file");
        System.out.println("Saving bouquet...");
        if (isBouquetMissing()) return;

        System.out.println("Enter filename name: ");
        String filename = input.nextLine().trim();

        fileStorage.saveBouquet(currentBouquet, filename);
        logger.info("The bouquet has been successfully saved to the file: {}", filename);
        System.out.println("Bouquet saved");
    }

    public void loadBouquetFromFile() {
        logger.info("Loading a bouquet from a file");
        System.out.println("Loading bouquet...");

        System.out.println("Enter filename name: ");
        String filename = input.nextLine().trim();

        System.out.println("Enter bouquet name: ");
        String bouquetName = input.nextLine().trim();

        bouquets.put(bouquetName, fileStorage.loadBouquet(filename));
        logger.info("Bouquet ‘{}’ successfully downloaded from file: {}", bouquetName, filename);
        System.out.println("Bouquet loaded");
    }

    public void removeItemFromBouquet() {
        logger.info("Removing an item from a bouquet");
        System.out.println("Removing a flower from a bouquet...");
        if (isBouquetMissing()) return;
        if (currentBouquet.size() == 0) {
            logger.warn("Attempt to remove an element from an empty bouquet");
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
            logger.info("The element ‘{}’ (index {}) has been successfully removed from the bouquet"
                    , selectedItem.name(), choice + 1);
            System.out.printf("The flower has been successfully removed: %s\n", selectedItem.name());
        } else {
            logger.warn("Incorrect index {} for deleting an element", choice + 1);
            System.out.println("Incorrect number");
        }
    }

    public void showGuide() {
        logger.info("Displaying user help");
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
        logger.info("Displaying the flower catalog");
        System.out.println(catalog);
    }

    public void exit() {
        logger.fatal("The program terminates. System.exit(0) called");
        System.exit(0);
    }

    public void replaceItem() {
        logger.info("Replacing an item in a bouquet");
        System.out.println("Replacing bouquet...");
        if (isBouquetMissing()) return;

        for (int i = 0; i < currentBouquet.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, currentBouquet.getItem(i));
        }
        System.out.println("Select the item you want to replace");
        int indexToRemove = getUserChoice();
        logger.debug("Index selected for deletion: {}", indexToRemove + 1);

        if (indexToRemove < 0 || indexToRemove >= currentBouquet.size()) {
            logger.warn("Incorrect index {} for replacing the element", indexToRemove + 1);
            System.out.println("Incorrect number");
            return;
        }
        IBouquetItem oldItem = currentBouquet.getItem(indexToRemove);

        System.out.println("Select the item from the catalog that you want to replace.");
        showCatalog();

        int indexToAdd = getUserChoice();
        logger.debug("The catalog index to be added has been selected: {}", indexToAdd + 1);

        IBouquetItem newItem = catalog.getItem(indexToAdd);
        if (newItem == null) {
            logger.warn("No item found in the catalog with index {}", indexToAdd + 1);
            System.out.println("There is no such item in the catalog");
            return;
        }

        currentBouquet.removeItem(indexToRemove);
        currentBouquet.addItem(newItem);
        logger.info("The element ‘{}’ has been successfully replaced with ‘{}’ in the bouquet"
                , oldItem.name(), newItem.name());
        System.out.printf("The subject %s was successfully replaced with %s\n", oldItem.name(), newItem.name());
    }

    public void switchBouquet() {
        logger.info("Switching between bouquets");
        System.out.println("Switching bouquet...");
        if (bouquets.isEmpty()) {
            logger.warn("Attempt to switch bouquets when none have been created yet");
            System.out.println("No bouquets created yet");
            return;
        }

        System.out.println("Available bouquets:");
        bouquets.forEach((bouquetName, bouquet) -> {
            String mark = (bouquet == currentBouquet) ? "Current" : "";
            System.out.printf("- %s %s\n", bouquetName, mark);
        });

        System.out.println("Enter the name of the bouquet you want to use:");
        String bouquetName = input.nextLine().trim();

        if (bouquets.containsKey(bouquetName)) {
            this.currentBouquet = bouquets.get(bouquetName);
            logger.info("The current bouquet has been changed to ‘{}’", bouquetName);
            System.out.printf("Bouquet changed to %s\n", bouquetName);
        } else {
            logger.warn("Bouquet named ‘{}’ not found", bouquetName);
            System.out.println("Bouquet not found");
        }
    }

    private int getUserChoice() {
        try {
            return Integer.parseInt(input.nextLine()) - 1;
        } catch (NumberFormatException e) {
            logger.error("Incorrect user input (a number was expected)", e);
            return -1;
        }
    }

    private boolean isBouquetMissing() {
        if (currentBouquet == null) {
            logger.warn("Attempt to perform an operation when the current bouquet is missing");
            System.out.println("Bouquet not yet created");
            return true;
        }
        return false;
    }

    private void fillCatalog() {
        catalog.addItem(new Rose(80, true, Color.PINK, FreshnessLevel.FRESH, 80.7));
        catalog.addItem(new Rose(80, true, Color.WHITE, FreshnessLevel.MEDIUM, 70.5));
        catalog.addItem(new Rose(80, true, Color.RED, FreshnessLevel.FRESH, 85.3));
        catalog.addItem(new Tulip(65, Color.RED, FreshnessLevel.FRESH, 60.0, true));
        catalog.addItem(new Tulip(65, Color.WHITE, FreshnessLevel.OLD, 63.2, false));
        catalog.addItem(new Tulip(65, Color.PURPLE, FreshnessLevel.MEDIUM, 57.8, false));
        catalog.addItem(new Tulip(65, Color.YELLOW, FreshnessLevel.FRESH, 60.0, true));
        catalog.addItem(new Gypsophila(90, Color.GREEN, FreshnessLevel.FRESH, 45.0));
        catalog.addItem(new Gypsophila(90, Color.WHITE, FreshnessLevel.OLD, 50.6));
        catalog.addItem(new Gypsophila(90, Color.BLUE, FreshnessLevel.OLD, 51.4));
        catalog.addItem(new Gypsophila(90, Color.PINK, FreshnessLevel.MEDIUM, 47.8));
        catalog.addItem(new Chamomile(35, Color.WHITE, FreshnessLevel.FRESH, 40));
        catalog.addItem(new Chamomile(35, Color.YELLOW, FreshnessLevel.FRESH, 37));
        catalog.addItem(new Accessory("Ribbon", 50));
        catalog.addItem(new Accessory("Flower wrapping paper", 70));
        catalog.addItem(new Accessory("Lace", 40));
        catalog.addItem(new Accessory("Floristic foam", 500));
        logger.info("The catalog of flowers and accessories is filled with items");
    }
}
