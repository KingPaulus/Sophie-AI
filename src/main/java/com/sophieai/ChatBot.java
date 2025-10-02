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
            } else if (input.contains("hallo") || input.contains("hi")) {
                System.out.println("Sophie-AI: Hallo! Wie geht es dir?");
            } else if (input.contains("wie geht")) {
                System.out.println("Sophie-AI: Mir geht’s super, danke der Nachfrage!");
            } else if (input.contains("wer bist du")) {
                System.out.println("Sophie-AI: Ich bin deine kleine KI-Assistentin 🤖✨");
            } else {
                System.out.println("Sophie-AI: Interessant... erzähl mir mehr!");
            }
        }
    }
}
