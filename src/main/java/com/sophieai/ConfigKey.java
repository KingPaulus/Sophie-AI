package com.sophieai;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigKey {
    private static final Properties props = new Properties();

    static {
        try (InputStream input = ConfigKey.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("⚠️ config.properties nicht gefunden. Bitte kopiere config.properties.example zu config.properties und trage deinen Key ein.");
            }
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("⚠️ Fehler beim Laden der config.properties", e);
        }
    }

    public static String getKey(String keyName) {
        return props.getProperty(keyName);
    }
}
