package ua.lpnu.manager;

import ua.lpnu.domain.bouquet.Bouquet;
import ua.lpnu.domain.FlowerCatalog;
import ua.lpnu.storage.FileStorage;
import ua.lpnu.domain.bouquet.IBouquetItem;

import java.util.List;
import java.util.Scanner;

public class FlowerShopManager {
    private final Bouquet currentBouquet = new Bouquet();
    private final FlowerCatalog catalog = new FlowerCatalog();
    private FileStorage fileStorage;
    private final Scanner input = new Scanner(System.in);


    public void createNewBouquet() {
        System.out.println("Creating bouquet...");
        System.out.println("Select from the catalog what you want to add to the bouquet");
        showCatalog();
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

    public void removeFlowerFromBouquet() {
        System.out.println("Removing a flower from a bouquet...");
        showBouquetInfo();
        System.out.println("Removing a flower from a bouquet...");

    }

    public void showGuide() {

    }

    public void showCatalog() {
        System.out.println(catalog);
    }

    public void exit() {
        System.exit(0);
    }

    public void replaceFlower() {

    }

    private int getUserChoice() {
        try {
            return Integer.parseInt(input.nextLine()) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

}
