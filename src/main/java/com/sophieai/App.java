package com.sophieai;

import java.util.Scanner;
import smile.classification.LogisticRegression;

public class App {
    public static void main(String[] args) {
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "error");

        System.out.println("Willkommen bei Sophie-AI 🧠✨");

        double[][] features = {
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

        int[] labels = {0,0,1,1,2,2,3,3,4,4};

        // Modell trainieren
        LogisticRegression model = LogisticRegression.fit(features, labels);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Größe (cm) eingeben: ");
        double groesse = scanner.nextDouble();
        System.out.print("Gewicht (kg) eingeben: ");
        double gewicht = scanner.nextDouble();

        double[] neuesTier = {groesse, gewicht};
        int prediction = model.predict(neuesTier);

        // Tiernamen zu Labels
        String[] tierNamen = {"Katze", "Hund", "Maus", "Hamster", "Kaninchen"};

        System.out.println("Vorhersage: " + tierNamen[prediction]);
    }
}
