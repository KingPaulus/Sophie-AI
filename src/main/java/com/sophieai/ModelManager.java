package com.sophieai;

import smile.classification.LogisticRegression;

import java.io.*;
import java.util.*;

public class ModelManager {

    private final String modelDir = "models"; // Ordner für Modelle

    public ModelManager() {
        File dir = new File(modelDir);
        if (!dir.exists()) dir.mkdir();
    }

    // Hilfsmethode: Dateiname bereinigen
    private String sanitizeFileName(String name) {
        name = name.replace(".", "-").replace(",", "-");
        if (!name.endsWith(".dat")) name = name + ".dat";
        return name;
    }

    // Speichern eines Modells
    private void saveModel(LogisticRegression model, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(modelDir + "/" + filename))) {
            oos.writeObject(model);
            System.out.println("✅ Modell gespeichert: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Laden eines Modells
    private LogisticRegression loadModel(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(modelDir + "/" + filename))) {
            LogisticRegression model = (LogisticRegression) ois.readObject();
            System.out.println("✅ Modell geladen: " + filename);
            return model;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ Modell konnte nicht geladen werden.");
            return null;
        }
    }

    // Alle Modelle auflisten
    private List<String> listModels() {
        File folder = new File(modelDir);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".dat"));
        List<String> modelNames = new ArrayList<>();
        if (files != null) {
            for (File f : files) modelNames.add(f.getName());
        }
        return modelNames;
    }

    // Start des Datenmanagement-Menüs
    public void startDataManager() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n📂 Datenmanagement:");
            System.out.println("1 - Tier-Modell speichern/laden");
            System.out.println("2 - Wetter-Modell speichern/laden");
            System.out.println("3 - Verfügbare Modelle anzeigen");
            System.out.println("4 - Zurück ins Hauptmenü");
            System.out.print("Auswahl: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> manageModel(scanner, "Tier");
                case 2 -> manageModel(scanner, "Wetter");
                case 3 -> {
                    List<String> models = listModels();
                    if (models.isEmpty()) System.out.println("⚠️ Keine Modelle gespeichert.");
                    else {
                        System.out.println("📂 Gespeicherte Modelle:");
                        models.forEach(m -> System.out.println("- " + m));
                    }
                }
                case 4 -> running = false;
                default -> System.out.println("Ungültige Eingabe!");
            }
        }
    }

    // Verwaltung für einen Klassifikator
    private void manageModel(Scanner scanner, String type) {
        LogisticRegression model = null;
        String modelNamePrefix = type.toLowerCase() + "_";

        System.out.println("\n" + type + " Modell:");
        System.out.println("1 - Speichern");
        System.out.println("2 - Laden");
        System.out.print("Auswahl: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.print("Dateiname eingeben: ");
            String name = sanitizeFileName(modelNamePrefix + scanner.nextLine());

            if (type.equals("Tier")) {
                TierClassifier classifier = new TierClassifier();
                saveModel(classifier.getModel(), name);
            } else if (type.equals("Wetter")) {
                WeatherClassifier weather = new WeatherClassifier();
                saveModel(weather.getModel(), name);
            }

        } else if (choice == 2) {
            List<String> models = listModels();
            models.removeIf(m -> !m.startsWith(modelNamePrefix)); // nur passende Modelle

            if (models.isEmpty()) {
                System.out.println("⚠️ Keine passenden Modelle gefunden!");
                return;
            }

            System.out.println("Verfügbare Modelle:");
            for (int i = 0; i < models.size(); i++) System.out.println((i + 1) + " - " + models.get(i));

            System.out.print("Wähle ein Modell: ");
            int sel = scanner.nextInt();
            scanner.nextLine();

            if (sel > 0 && sel <= models.size()) {
                String filename = models.get(sel - 1);
                model = loadModel(filename);

                if (model != null) {
                    System.out.println("🔄 Modell geladen: " + filename);
                    // Optional: hier kannst du das Modell in einen Klassifikator setzen
                    // z.B. TierClassifier.setModel(model) oder WeatherClassifier.setModel(model)
                }
            } else {
                System.out.println("Ungültige Auswahl!");
            }
        } else {
            System.out.println("Ungültige Eingabe!");
        }
    }
}
