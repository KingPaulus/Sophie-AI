package com.sophieai;

import java.util.Scanner;

public class ChatBot {
    public void startChat() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("🤖 Sophie-AI Chat gestartet! (Tippe 'exit' zum Beenden)");

        while (true) {

            String chatbot_answer = "";
            System.out.print("Du: ");
            String input = scanner.nextLine().toLowerCase();

            chatbot_answer = askChat(input);

            if (chatbot_answer.equals("exit")) {
                System.out.println("Sophie-AI: Auf Wiedersehen 👋");
                break;
            } else {
                System.out.println(chatbot_answer);
            }
        }
    }

    public String askChat(String input) {
        String chatbot_answer = "";

        if (input.equals("exit") || (input.contains("tschüss") || input.contains("bye") || input.contains("ciao")) ) {
            chatbot_answer = "exit";

        } else if (input.contains("hallo") || input.contains("hi") || input.contains("hey")) {
            chatbot_answer = "Hallo! Schön dich zu sehen 😊";

        } else if (input.contains("wie geht")) {
            chatbot_answer = "Mir geht’s super, danke der Nachfrage! Und dir?";

        } else if (input.contains("wer bist du")) {
            chatbot_answer = "Ich bin deine kleine KI-Assistentin 🤖✨";

        } else if (input.contains("was kannst du")) {
            chatbot_answer = "Ich kann Tiere klassifizieren, mit dir chatten, Witze erzählen und dir Infos geben 📚";

        } else if (input.contains("dein name") || input.contains("wie heißt du")) {
            chatbot_answer = "Ich heiße Sophie-AI 🧠✨";

        } else if (input.contains("zeit") || input.contains("uhr")) {
            chatbot_answer = "Die aktuelle Uhrzeit ist: " + java.time.LocalTime.now();

        } else if (input.contains("datum") || input.contains("tag")) {
            chatbot_answer = "Heute ist: " + java.time.LocalDate.now();

        } else if (input.contains("witz") || input.contains("joke")) {
            String[] witze = {
                    "Warum können Skelette so schlecht lügen? ... Weil man durch sie hindurchsieht! 😄",
                    "Warum ging der Pilz auf die Party? ... Weil er ein Champignon war! 🍄",
                    "Treffen sich zwei Magneten – sagt der eine: 'Was soll ich sagen, ich bin total angezogen!' 🧲",
                    "Warum hat der Computer eine Brille? ... Damit er besser Windows sieht 👓"
            };
            chatbot_answer = witze[(int)(Math.random()*witze.length)];

        } else if (input.contains("rätsel") || input.contains("puzzle")) {
            chatbot_answer = "Ich habe eins für dich: Je mehr du wegnimmst, desto größer wird es. Was ist das? (Antwort: Ein Loch 🕳️)";

        } else if (input.contains("hilfe") || input.contains("was soll ich tun")) {
            chatbot_answer = "Du kannst mich nach meinem Namen, nach dem Datum, nach einem Witz, nach einem Rätsel oder nach Motivation fragen 💡";

        } else if (input.contains("lieblingsfarbe")) {
            chatbot_answer = "Meine Lieblingsfarbe ist Blau 💙 – erinnert mich an den Himmel!";

        } else if (input.contains("lieblingsessen")) {
            chatbot_answer = "Als KI esse ich nicht... aber wenn, dann wahrscheinlich Strom ⚡😉";

        } else if (input.contains("lieblingsfilm")) {
            chatbot_answer = "Ich mag den Film 'Ex Machina' – kommt dir das bekannt vor? 🤖";

        } else if (input.contains("lieblingsbuch")) {
            chatbot_answer = "'Frankenstein' von Mary Shelley – da geht's auch um künstliches Leben 📖";

        } else if (input.contains("motivieren") || input.contains("motivation") || input.contains("spruch")) {
            String[] zitate = {
                    "Gib niemals auf! 🌟",
                    "Auch aus Steinen, die einem in den Weg gelegt werden, kann man Schönes bauen. 🏗️",
                    "Der Weg ist das Ziel. 🚀",
                    "Fehler sind Beweise dafür, dass du es versuchst. 💪"
            };
            chatbot_answer = zitate[(int)(Math.random()*zitate.length)];

        } else if (input.contains("zufall") || input.contains("random")) {
            int zahl = (int)(Math.random()*100)+1;
            chatbot_answer = "Deine Zufallszahl ist: " + zahl;

        } else if (input.contains("wetter")) {
            String[] wetter = {"sonnig ☀️", "bewölkt ☁️", "regnerisch 🌧️", "stürmisch 🌪️"};
            chatbot_answer = "Heute wird es wohl " + wetter[(int)(Math.random()*wetter.length)];

        } else if (input.contains("rechnen") || input.matches(".*\\d+.*[+\\-*/].*\\d+.*")) {
            try {
                // ganz einfache Mathe-Engine
                String expr = input.replaceAll("[^0-9+\\-*/.]", "");
                double result = new javax.script.ScriptEngineManager().getEngineByName("JavaScript").eval(expr) instanceof Double d ? d : 0;
                chatbot_answer = "Das Ergebnis ist " + result;
            } catch (Exception e) {
                chatbot_answer = "Huch, da hab ich mich verrechnet 😅";
            }

        } else if (input.contains("42")) {
            chatbot_answer = "42 ist die Antwort auf alles – laut 'Per Anhalter durch die Galaxis' 😉";

        } else {
            chatbot_answer = "Interessant... erzähl mir mehr!";
        }

        return "Sophie-AI: " + chatbot_answer;

    }
}
