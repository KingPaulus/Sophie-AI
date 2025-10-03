![Installtion Logo](img/readme/Installation.png)

# Sophie-AI 🤖✨ Installation

Dieses Projekt nutzt die OpenAI API (ChatGPT), um Antworten in natürlicher Sprache zu erzeugen.

## Voraussetzungen
- Java 19+
- Maven
- Einen OpenAI API Key: [Hier generieren](https://platform.openai.com/account/api-keys)

## Einrichtung
1. Klone das Repository ```git clone http```
2. Erstelle die .env datei `mv config.properties.example config.properties`
3. Trage deinen API-Key ein:

![Installtion Logo](img/readme/OpenAI.png)

### 1. API-Schlüssel erzeugen

1. Melde dich unter https://platform.openai.com an.
2. Gehe zu **API Keys** (in deinem Account-Dashboard).
3. Klicke auf **“Create new secret key”**.
4. Wähle die Berechtigungen:
    - **All** (voller Zugriff) – Standard, am flexibelsten
    - **Restricted** – wenn du nur bestimmte Endpunkte erlauben möchtest
    - **Read Only** – nur Lesezugriff (nur sinnvoll, wenn du nicht mit ChatGPT antwortest)
5. Kopiere den erzeugten Schlüssel; du kannst ihn später **nicht** erneut einsehen.

### 2. Schlüssel sichern in `config.properties`

- Erstelle eine Datei `config.properties` im Projekt-Root (sie wird in `.gitignore` ausgeschlossen).
- Trage dort ein:

```env
OPENAI_API_KEY=sk-XXXXXXXXXXXXXXXXXXXXXXXX
```



