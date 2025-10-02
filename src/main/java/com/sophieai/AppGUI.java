package com.sophieai;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AppGUI {

    private JFrame frame;
    private JTextArea outputArea;

    public AppGUI() {
        frame = new JFrame("Sophie-AI 🧠✨");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // wichtig: schließt nur das Fenster
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        frame.add(scrollPane, BorderLayout.CENTER);

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
        btnExit.addActionListener(e -> frame.dispose()); // schließt nur das GUI-Fenster

        frame.setVisible(true);
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
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Ungültige Eingabe!", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void startChat() {
        ChatBot chat = new ChatBot();
        outputArea.append("Chat gestartet! (Konsole öffnen für Chat-Interaktion)\n");
        chat.startChat();
    }

}
