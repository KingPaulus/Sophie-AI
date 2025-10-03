package com.sophieai;

import com.apple.eawt.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AppGUI {

    private JFrame frame;
    private JTextArea outputArea;
    private JLabel imageLabel; // Bildanzeige

    public AppGUI() {
        frame = new JFrame("Sophie-AI 🧠✨");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());

        // Logo Auf dem Startscreen
        ImageIcon startLogo = new ImageIcon("img/icon_xlarge_transparent.png");
        Image scaledImage = startLogo.getImage().getScaledInstance(430, 430, Image.SCALE_SMOOTH);
        ImageIcon scaledLogo = new ImageIcon(scaledImage);

// JLabel mit Logo und Abstand nach unten
        JLabel logoLabel = new JLabel(scaledLogo);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // oben/unten Abstand

        frame.add(logoLabel, BorderLayout.NORTH);

        // Fenster-Icon
        ImageIcon icon = new ImageIcon("img/icon_large.png");
        frame.setIconImage(icon.getImage());

        // Icon für macOS Dock
        Taskbar taskbar = Taskbar.getTaskbar();
        if (taskbar.isSupported(Taskbar.Feature.ICON_IMAGE)) {
            taskbar.setIconImage(icon.getImage());
        }

        // Mittig Platzieren
        frame.setLocationRelativeTo(null);

        // Textausgabe
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Bildausgabe
        imageLabel = new JLabel("", SwingConstants.CENTER);
        frame.add(imageLabel, BorderLayout.EAST);

        // Menü-Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4, 10, 10));

        JButton btnTier = new JButton("Tiere abfragen");
        JButton btnWetter = new JButton("Wettervorhersage");
        JButton btnChat = new JButton("Chat starten");
        JButton btnExit = new JButton("Schließen");

        buttonPanel.add(btnTier);
        buttonPanel.add(btnWetter);
        buttonPanel.add(btnChat);
        buttonPanel.add(btnExit);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        btnTier.addActionListener(e -> startTierAbfrage());
        btnWetter.addActionListener(e -> startWetterVorhersage());
        btnChat.addActionListener(e -> startChat());
        btnExit.addActionListener(e -> frame.dispose());

        showSplash();
        //frame.setVisible(true);
    }

    private void startTierAbfrage() {
        TierClassifier classifier = new TierClassifier();
        String groesseStr = JOptionPane.showInputDialog(frame, "Größe (cm) eingeben:");
        String gewichtStr = JOptionPane.showInputDialog(frame, "Gewicht (kg) eingeben:");

        try {
            double groesse = Double.parseDouble(groesseStr);
            double gewicht = Double.parseDouble(gewichtStr);
            int label = classifier.predict(new double[]{groesse, gewicht});
            String tier = classifier.getTierName(label);
            outputArea.append("Vorhersage Tier: " + tier + "\n");
            showImage(tier);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Ungültige Eingabe!", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void startWetterVorhersage() {
        WeatherClassifier weather = new WeatherClassifier();
        String tempStr = JOptionPane.showInputDialog(frame, "Temperatur (°C) eingeben:");
        String humidityStr = JOptionPane.showInputDialog(frame, "Luftfeuchtigkeit (%) eingeben:");
        String windStr = JOptionPane.showInputDialog(frame, "Windgeschwindigkeit (km/h) eingeben:");

        try {
            double temp = Double.parseDouble(tempStr);
            double humidity = Double.parseDouble(humidityStr);
            double wind = Double.parseDouble(windStr);
            int label = weather.predict(new double[]{temp, humidity, wind});
            String wetter = weather.getWeatherName(label);
            outputArea.append("Vorhersage Wetter: " + wetter + "\n");
            showImage(wetter);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Ungültige Eingabe!", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void startChat() {
        JPanel chatPanel = new JPanel(new BorderLayout());
        JTextField inputField = new JTextField();
        JButton sendButton = new JButton("Senden");

        chatPanel.add(inputField, BorderLayout.CENTER);
        chatPanel.add(sendButton, BorderLayout.EAST);

        frame.add(chatPanel, BorderLayout.NORTH);
        frame.revalidate();

        sendButton.addActionListener(e -> {
            String input = inputField.getText().trim();
            if (input.isEmpty()) return;

            outputArea.append("Du: " + input + "\n");
            String response = getChatResponse(input);
            outputArea.append("Sophie-AI: " + response + "\n");
            inputField.setText("");
        });
    }

    private String getChatResponse(String input) {
        input = input.toLowerCase();
        if (input.contains("hallo") || input.contains("hi")) return "Hallo! Wie geht es dir?";
        if (input.contains("wie geht")) return "Mir geht’s super, danke der Nachfrage!";
        if (input.contains("wer bist du")) return "Ich bin deine kleine KI-Assistentin 🤖✨";
        if (input.equals("exit")) return "Auf Wiedersehen 👋";
        return "Interessant... erzähl mir mehr!";
    }

    // Bild-Anzeige je nach Ergebnis
    private void showImage(String prediction) {
        String path = null;
        switch (prediction.toLowerCase()) {
            case "katze" -> path = "img/tiere/katze.png";
            case "hund" -> path = "img/tiere/hund.png";
            case "maus" -> path = "img/tiere/maus.png";
            case "hamster" -> path = "img/tiere/hamster.png";
            case "kaninchen" -> path = "img/tiere/kaninchen.png";
            case "sonnig" -> path = "img/wetter/sonne.png";
            case "regnerisch" -> path = "img/wetter/regnerisch.png";
            case "bewölkt" -> path = "img/wetter/bewoelkt.png";
        }

        if (path != null) {
            ImageIcon icon = new ImageIcon(path);
            Image scaled = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaled));
        } else {
            imageLabel.setIcon(null);
        }
    }

    private void showSplash() {
        JWindow splash = new JWindow();

        // Splash-Logo laden und skalieren
        ImageIcon splashIcon = new ImageIcon("img/icon_xlarge_transparen_withtext.png");
        Image scaled = splashIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        JLabel splashLabel = new JLabel(new ImageIcon(scaled));

        splash.add(splashLabel);
        splash.setSize(320, 340);   // etwas größer als Bild
        splash.setLocationRelativeTo(null);
        splash.setVisible(true);

        // Nach 2 Sekunden Splash schließen
        new Timer(2000, e -> {
            splash.dispose();
            frame.setVisible(true);
        }).start();
    }
}
