package ua.lpnu.storage;

import ua.lpnu.domain.Accessory;
import ua.lpnu.domain.Color;
import ua.lpnu.domain.bouquet.Bouquet;
import ua.lpnu.domain.bouquet.IBouquetItem;
import ua.lpnu.domain.flower.*;

import java.io.*;
import java.util.List;

public class FileStorage {
    public void saveBouquet(Bouquet bouquet, String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            List<IBouquetItem> items = bouquet.getItems();

            for (IBouquetItem item : items) {
                String line = convertItemToString(item);
                if (line != null) {
                    bw.write(line);
                    bw.newLine();
                }
            }
            System.out.printf("bouquet saved to file: %s\n", filename);
        } catch (IOException e) {
            System.out.printf("Error saving bouquet %s\n", e.getMessage());
        }
    }

    public Bouquet loadBouquet(String filename) {
        Bouquet bouquet = new Bouquet();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                IBouquetItem item = parseItemFromString(line);
                if (item != null) {
                    bouquet.addItem(item);
                }
            }
            System.out.printf("bouquet loaded from file: %s\n", filename);
        } catch (IOException e) {
            System.out.printf("Error loading bouquet %s\n", e.getMessage());
        }
        return bouquet;
    }

    private String convertItemToString(IBouquetItem item) {
        StringBuilder sb = new StringBuilder();

        switch (item) {
            case Rose rose -> sb.append("Rose;").append(rose.name())
                    .append(";").append(rose.price()).append(";")
                    .append(rose.getStemLength()).append(";")
                    .append(rose.getFreshness()).append(";")
                    .append(rose.getColor()).append(";")
                    .append(rose.getHasSpikes());
            case Tulip tulip -> sb.append("Tulip;").append(tulip.name())
                    .append(";").append(tulip.price()).append(";")
                    .append(tulip.getStemLength()).append(";")
                    .append(tulip.getFreshness()).append(";")
                    .append(tulip.getColor()).append(";")
                    .append(tulip.isClosed());
            case Chamomile chamomile -> sb.append("Chamomile;").append(chamomile.name())
                    .append(";").append(chamomile.price()).append(";")
                    .append(chamomile.getStemLength()).append(";")
                    .append(chamomile.getFreshness()).append(";")
                    .append(chamomile.getColor());
            case Gypsophila gypsophila -> sb.append("Gypsophila;").append(gypsophila.name())
                    .append(";").append(gypsophila.price()).append(";")
                    .append(gypsophila.getStemLength()).append(";")
                    .append(gypsophila.getFreshness()).append(";")
                    .append(gypsophila.getColor());
            case Accessory(String name, double price) -> sb.append("Accessory;").append(name)
                    .append(";").append(price);
            case null, default -> {
                return null;
            }
        }
        return sb.toString();
    }

    private IBouquetItem parseItemFromString(String line) {
        String[] items = line.split(";");

        String type = items[0];
        String name = items[1];
        double price = Double.parseDouble(items[2]);

        switch (type) {
            case "Rose":
                double rLength = Double.parseDouble(items[3]);
                FreshnessLevel rFreshness = FreshnessLevel.valueOf(items[4]);
                Color rColor = Color.valueOf(items[5]);
                boolean hasSpikes = Boolean.parseBoolean(items[6]);

                return new Rose(price, hasSpikes, rColor, rFreshness, rLength);
            case "Tulip":
                double tLength = Double.parseDouble(items[3]);
                FreshnessLevel tFreshness = FreshnessLevel.valueOf(items[4]);
                Color tColor = Color.valueOf(items[5]);
                boolean isClosed = Boolean.parseBoolean(items[6]);

                return new Tulip(price, tColor, tFreshness, tLength, isClosed);
            case "Chamomile":
                double cLength = Double.parseDouble(items[3]);
                FreshnessLevel cFreshness = FreshnessLevel.valueOf(items[4]);
                Color cColor = Color.valueOf(items[5]);

                return new Chamomile(price, cColor, cFreshness, cLength);
            case "Gypsophila":
                double gLength = Double.parseDouble(items[3]);
                FreshnessLevel gFreshness = FreshnessLevel.valueOf(items[4]);
                Color gColor = Color.valueOf(items[5]);

                return new Gypsophila(price, gColor, gFreshness, gLength);
            case "Accessory":
                return new Accessory(name, price);
            default:
                return null;
        }
    }
}
