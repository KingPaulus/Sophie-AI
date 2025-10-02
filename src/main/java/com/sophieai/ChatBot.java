package com.sophieai;

import java.util.Scanner;

public class ChatBot {
    public void startChat() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("🤖 Sophie-AI Chat gestartet! (Tippe 'exit' zum Beenden)");

        while (true) {
            System.out.print("Du: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("exit")) {
                System.out.println("Sophie-AI: Auf Wiedersehen 👋");
                break;

            } else if (input.contains("hallo") || input.contains("hi") || input.contains("hey")) {
                System.out.println("Sophie-AI: Hallo! Schön dich zu sehen 😊");

            } else if (input.contains("wie geht")) {
                System.out.println("Sophie-AI: Mir geht’s super, danke der Nachfrage! Und dir?");

            } else if (input.contains("wer bist du")) {
                System.out.println("Sophie-AI: Ich bin deine kleine KI-Assistentin 🤖✨");

            } else if (input.contains("was kannst du")) {
                System.out.println("Sophie-AI: Ich kann Tiere klassifizieren, mit dir chatten und bald noch viel mehr!");

            } else if (input.contains("dein name") || input.contains("wie heißt du")) {
                System.out.println("Sophie-AI: Ich heiße Sophie-AI 🧠✨");

            } else if (input.contains("zeit") || input.contains("uhr")) {
                System.out.println("Sophie-AI: Die aktuelle Uhrzeit ist: " + java.time.LocalTime.now());

            } else if (input.contains("datum") || input.contains("tag")) {
                System.out.println("Sophie-AI: Heute ist: " + java.time.LocalDate.now());

            } else if (input.contains("witz") || input.contains("joke")) {
                System.out.println("Sophie-AI: Warum können Skelette so schlecht lügen? ... Weil man durch sie hindurchsieht! 😄");

            } else if (input.contains("hilfe") || input.contains("was soll ich tun")) {
                System.out.println("Sophie-AI: Du kannst mich nach meinem Namen, nach dem Datum, nach einem Witz fragen – oder einfach 'exit' eingeben um zu beenden.");

            } else if (input.contains("lieblingsfarbe")) {
                System.out.println("Sophie-AI: Meine Lieblingsfarbe ist Blau 💙 – erinnert mich an den Himmel!");

            } else if (input.contains("lieblingsessen")) {
                System.out.println("Sophie-AI: Als KI esse ich nicht... aber wenn, dann wahrscheinlich Strom ⚡😉");

            } else if (input.contains("lieblingsfilm")) {
                System.out.println("Sophie-AI: Ich mag den Film 'Ex Machina' – kommt dir das bekannt vor? 🤖");

            } else if (input.contains("tschüss") || input.contains("bye") || input.contains("ciao")) {
                System.out.println("Sophie-AI: Tschüss! War schön mit dir zu reden 👋");
                break;

            } else {
                System.out.println("Sophie-AI: Interessant... erzähl mir mehr!");
            }

        }
    }
}
