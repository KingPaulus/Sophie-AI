package com.sophieai;

import smile.classification.LogisticRegression;

public class WeatherClassifier {

    private double[][] features;   // z.B. Temperatur, Luftfeuchtigkeit, Wind
    private int[] labels;          // 0=sonnig, 1=regnerisch, 2=bewölkt
    private LogisticRegression model;
    private String[] weatherNames = {"Sonnig", "Regnerisch", "Bewölkt"};

    public WeatherClassifier() {
        // Trainingsdaten (sehr einfach, Dummy-Daten)
        features = new double[][] {
                {30, 40, 5},  // sonnig
                {32, 35, 4},  // sonnig
                {20, 80, 10}, // regnerisch
                {18, 90, 12}, // regnerisch
                {25, 60, 8},  // bewölkt
                {22, 70, 6}   // bewölkt
        };
        labels = new int[]{0,0,1,1,2,2};

        // Modell trainieren
        model = LogisticRegression.fit(features, labels);
    }

    // Vorhersage-Methode
    public int predict(double[] inputFeatures) {
        return model.predict(inputFeatures);
    }

    public String getWeatherName(int label) {
        return weatherNames[label];
    }

    // Getter/Setter für ModelManager
    public LogisticRegression getModel() {
        return model;
    }

    public void setModel(LogisticRegression model) {
        this.model = model;
    }

    // Interaktive Konsole
    public void startInteractiveSession() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.print("Temperatur (°C, 0 zum Beenden): ");
            double temp = scanner.nextDouble();
            if (temp == 0) break;

            System.out.print("Luftfeuchtigkeit (%): ");
            double humidity = scanner.nextDouble();

            System.out.print("Windgeschwindigkeit (km/h): ");
            double wind = scanner.nextDouble();

            double[] input = {temp, humidity, wind};
            int prediction = predict(input);
            System.out.println("Vorhersage: " + getWeatherName(prediction));
        }

        System.out.println("Beende Wettervorhersage...");
    }
}
