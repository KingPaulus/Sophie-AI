package com.sophieai;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChatChatGPT {

    private static String API_KEY;

    public static void config() {
        String apiKey = ConfigKey.getKey("OPENAI_API_KEY");
        System.out.println("API Key geladen: " + (apiKey != null ? "✅" : "❌"));
        API_KEY = apiKey;
    }

    public void startInteractiveSession() {
        config();
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.print("Frage Chatgpt etwas (exit zum Beenden): ");
            String question = scanner.next();

            if(question.equalsIgnoreCase("exit")) {
                running = false;
                break;
            } else {
                askChatGPT(question);
            }
        }
    }

    public static String askChatGPT(String question) {

        try {
            URL url = new URL("https://api.openai.com/v1/chat/completions");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // Header
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + API_KEY);

            // JSON Body
            String jsonInput = "{"
                    + "\"model\": \"gpt-3.5-turbo\","
                    + "\"messages\": [{\"role\": \"user\", \"content\": \"" + question + "\"}]"
                    + "}";

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInput.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Antwort lesen
            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }

            // Einfach die Antwort aus dem JSON ziehen (sehr simpel, ohne externe Lib)
            String result = response.toString();
            int start = result.indexOf("\"content\":\"") + 11;
            int end = result.indexOf("\"", start);
            if (start > 10 && end > start) {
                return result.substring(start, end);
            }

            return "Keine gültige Antwort von ChatGPT bekommen.";

        } catch (Exception e) {
            e.printStackTrace();
            return "Fehler beim Verbinden mit ChatGPT: " + e.getMessage();
        }
    }
}
