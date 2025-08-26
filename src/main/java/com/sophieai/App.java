package com.sophieai;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Willkommen bei Sophie-AI 🧠✨");

        System.out.println("Was möchtest du tun?");
        System.out.println("1 - Tiere abfragen");
        System.out.println("2 - Exit");
        int auswahl = scanner.nextInt();

        if (auswahl == 1) {
            TierClassifier classifier = new TierClassifier();
            classifier.startInteractiveSession();
        } else {
            System.out.println("Auf Wiedersehen!");
        }
    }
}
