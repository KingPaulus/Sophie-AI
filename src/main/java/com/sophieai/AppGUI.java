package com.sophieai;

import com.apple.eawt.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AppGUI {

    private JFrame frame;          // Das Hauptfenster
    private JTextArea outputArea;  // Textausgaben (Log, Chat, Vorhersagen)
    private JScrollPane scrollPane; // Scrollbarer Container für das Textfeld
    private JPanel contentPanel;   // Zentraler Bereich (Chat, Wetter, Tiere etc.)
    private JLabel imageLabel;     // Bildanzeige für Tiere/Wetter
    private JLabel logoLabel;      // Start-Logo (optional, oben)


    public AppGUI() {
        frame = new JFrame("Sophie-AI 🧠✨");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Beendet App komplett
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // Fenster zentrieren

        // Logo Auf dem Startscreen
        ImageIcon startLogo = new ImageIcon("img/start_logo.png");
        Image scaledImage = startLogo.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        logoLabel = new JLabel(new ImageIcon(scaledImage));
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(logoLabel, BorderLayout.NORTH);


        // Fenster-Icon
        ImageIcon icon = new ImageIcon("img/icon_large.png");
        frame.setIconImage(icon.getImage());

        // Icon für macOS Dock
        Taskbar taskbar = Taskbar.getTaskbar();
        if (taskbar.isSupported(Taskbar.Feature.ICON_IMAGE)) {
            taskbar.setIconImage(icon.getImage());
        }

        contentPanel = new JPanel(new BorderLayout());

        // Textfeld (scrollbar)
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        scrollPane = new JScrollPane(outputArea);

        // Bildlabel für Tier/Wetter
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Standard-Ansicht: Text + Bild
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(imageLabel, BorderLayout.SOUTH);

        frame.add(contentPanel, BorderLayout.CENTER);


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

        // Aktionen
        btnTier.addActionListener(e -> startTierAbfrage());
        btnWetter.addActionListener(e -> startWetterVorhersage());
        btnChat.addActionListener(e -> startChat());
        btnExit.addActionListener(e -> System.exit(0)); // Programm wirklich beenden


        showSplash();
        //frame.setVisible(true);
    }

    private void startTierAbfrage() {
        TierClassifier classifier = new TierClassifier();
        String groesseStr = JOptionPane.showInputDialog(frame, "Größe (cm) eingeben:");
        String gewichtStr = JOptionPane.showInputDialog(frame, "Gewicht (kg) eingeben:");
        logoLabel.setVisible(false);

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
        logoLabel.setVisible(false);

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
        contentPanel.removeAll();

        JPanel chatPanel = new JPanel(new BorderLayout());
        JTextField inputField = new JTextField();
        JButton sendButton = new JButton("Senden");

        chatPanel.add(inputField, BorderLayout.CENTER);
        chatPanel.add(sendButton, BorderLayout.EAST);

        // nur Chat + Textausgabe
        contentPanel.add(chatPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Bild explizit löschen
        imageLabel.setIcon(null);

        contentPanel.revalidate();
        contentPanel.repaint();

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
        ImageIcon splashIcon = new ImageIcon("img/starting_screen.png");
        Image scaled = splashIcon.getImage().getScaledInstance(320, 340, Image.SCALE_SMOOTH);
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
