package com.sophieai;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Willkommen bei Sophie-AI 🧠✨");

        boolean running = true;
        while (running) {
            System.out.println("\nWas möchtest du tun?");
            System.out.println("1 - Tiere abfragen");
            System.out.println("2 - Chat starten");
            System.out.println("3 - Datenmanagement");
            System.out.println("4 - Wetter Vorhersage");
            System.out.println("5 - Exit");
            int auswahl = scanner.nextInt();
            scanner.nextLine(); // Scanner-Puffer leeren

            if (auswahl == 1) {
                TierClassifier classifier = new TierClassifier();
                classifier.startInteractiveSession();
            } else if (auswahl == 2) {
                ChatBot chat = new ChatBot();
                chat.startChat();
            } else if (auswahl == 3) {
                TierClassifier classifier = new TierClassifier();
                ModelManager manager = new ModelManager();
                manager.startDataManager(classifier);
            } else if (auswahl == 4) {
                WeatherClassifier weather = new WeatherClassifier();
                weather.startInteractiveSession();
            } else if (auswahl == 5) {
                System.out.println("Auf Wiedersehen!");
                running = false;
            } else {
                System.out.println("Ungültige Eingabe, bitte erneut versuchen!");
            }
        }
    }
}
