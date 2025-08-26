package com.sophieai;

import javax.swing.*;
import java.awt.event.*;

public class TierGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sophie-AI Tierklassifikation");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField groesseField = new JTextField(5);
        JTextField gewichtField = new JTextField(5);
        JButton predictButton = new JButton("Vorhersage");

        JLabel resultLabel = new JLabel("Vorhersage erscheint hier");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Größe (cm):"));
        panel.add(groesseField);
        panel.add(new JLabel("Gewicht (kg):"));
        panel.add(gewichtField);
        panel.add(predictButton);
        panel.add(resultLabel);

        frame.add(panel);
        frame.setVisible(true);

        TierClassifier classifier = new TierClassifier();

        predictButton.addActionListener(e -> {
            double groesse = Double.parseDouble(groesseField.getText());
            double gewicht = Double.parseDouble(gewichtField.getText());
            int prediction = classifier.predict(new double[]{groesse, gewicht});
            resultLabel.setText("Vorhersage: " + classifier.getTierName(prediction));
        });
    }
}

