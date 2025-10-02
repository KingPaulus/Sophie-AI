package com.sophieai;

import java.util.Scanner;
import smile.classification.LogisticRegression;

public class TierClassifier {
    private double[][] features;
    private int[] labels;
    private LogisticRegression model;
    private String[] tierNamen = {"Katze", "Hund", "Maus", "Hamster", "Kaninchen"};

    public TierClassifier() {
        // Trainingsdaten
        features = new double[][] {
                {30, 5},   // Katze
                {35, 6},   // Katze
                {50, 20},  // Hund
                {60, 25},  // Hund
                {10, 0.02},// Maus
                {12, 0.03},// Maus
                {8, 0.05}, // Hamster
                {9, 0.06}, // Hamster
                {25, 2},   // Kaninchen
                {30, 2.5}  // Kaninchen
        };
        labels = new int[]{0,0,1,1,2,2,3,3,4,4};

        // Modell trainieren
        model = LogisticRegression.fit(features, labels);
    }

    // Neue Methode für GUI oder andere Klassen
    public int predict(double[] inputFeatures) {
        return model.predict(inputFeatures);
    }

    public String getTierName(int label) {
        return tierNamen[label];
    }

    public void startInteractiveSession() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while(running) {
            System.out.print("Größe (cm) eingeben (0 zum Beenden): ");
            double groesse = scanner.nextDouble();
            if(groesse == 0) break;

            System.out.print("Gewicht (kg) eingeben: ");
            double gewicht = scanner.nextDouble();

            double[] neuesTier = {groesse, gewicht};
            int prediction = model.predict(neuesTier);
            System.out.println("Vorhersage: " + tierNamen[prediction]);
        }

        System.out.println("Beende Tierabfrage...");
    }

    public LogisticRegression getModel() {
        return model;
    }

    public void setModel(LogisticRegression model) {
        this.model = model;
    }

}
