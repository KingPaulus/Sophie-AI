package com.sophieai;

import java.util.Scanner;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.simple.SimpleLogger;

public class App {
    static {
        // SLF4J NOP-Logger aktivieren
        System.setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "ERROR");
    }

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
                ModelManager manager = new ModelManager();
                manager.startDataManager();
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
