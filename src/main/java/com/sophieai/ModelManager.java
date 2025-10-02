package com.sophieai;

import smile.classification.LogisticRegression;

import java.io.*;
import java.util.*;

public class ModelManager {

    private final String modelDir = "models"; // Ordner für Modelle

    public ModelManager() {
        // Ordner anlegen, falls nicht vorhanden
        File dir = new File(modelDir);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    // Modell speichern mit flexiblem Namen
    public void saveModel(LogisticRegression model, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(modelDir + "/" + filename))) {
            oos.writeObject(model);
            System.out.println("✅ Modell gespeichert: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Modell laden
    public LogisticRegression loadModel(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(modelDir + "/" + filename))) {
            LogisticRegression model = (LogisticRegression) ois.readObject();
            System.out.println("✅ Modell geladen: " + filename);
            return model;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ Modell konnte nicht geladen werden.");
            return null;
        }
    }

    // Alle gespeicherten Modelle auflisten
    public List<String> listModels() {
        File folder = new File(modelDir);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".dat"));
        List<String> modelNames = new ArrayList<>();

        if (files != null) {
            for (File f : files) {
                modelNames.add(f.getName());
            }
        }
        return modelNames;
    }

    // Interaktives Menü
    public void startDataManager(TierClassifier classifier) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n📂 Datenmanagement:");
            System.out.println("1 - Modell speichern");
            System.out.println("2 - Modell laden");
            System.out.println("3 - Verfügbare Modelle anzeigen");
            System.out.println("4 - Zurück ins Hauptmenü");
            System.out.print("Auswahl: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Dateiname eingeben (z.B. tiere_v1.dat): ");
                    String saveName = scanner.nextLine();
                    saveModel(classifier.getModel(), saveName);
                }
                case 2 -> {
                    List<String> models = listModels();
                    if (models.isEmpty()) {
                        System.out.println("⚠️ Keine Modelle gefunden!");
                        break;
                    }
                    System.out.println("Verfügbare Modelle:");
                    for (int i = 0; i < models.size(); i++) {
                        System.out.println((i + 1) + " - " + models.get(i));
                    }
                    System.out.print("Wähle ein Modell: ");
                    int selection = scanner.nextInt();
                    scanner.nextLine();

                    if (selection > 0 && selection <= models.size()) {
                        String fileName = models.get(selection - 1);
                        LogisticRegression loaded = loadModel(fileName);
                        if (loaded != null) {
                            classifier.setModel(loaded);
                            System.out.println("🔄 Modell im Classifier gesetzt!");
                        }
                    } else {
                        System.out.println("Ungültige Auswahl!");
                    }
                }
                case 3 -> {
                    List<String> models = listModels();
                    if (models.isEmpty()) {
                        System.out.println("⚠️ Keine Modelle gespeichert.");
                    } else {
                        System.out.println("📂 Gespeicherte Modelle:");
                        models.forEach(m -> System.out.println("- " + m));
                    }
                }
                case 4 -> running = false;
                default -> System.out.println("Ungültige Eingabe!");
            }
        }
    }
}
