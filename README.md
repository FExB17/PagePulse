# 📸 PagePulse – Website Screenshot Tool mit Bot Detection

PagePulse ist ein kleines Java-Tool, das eine Webseite automatisiert besucht, einen Screenshot erstellt und prüft, ob der Zugriff blockiert wird (z. B. durch Bot-Detection wie HTTP 403).

---

## 🚀 Funktionen

- Webseite mit Selenium besuchen (Headless Chrome)
- Screenshot aufnehmen (lokale Datei & Base64)
- Bot-Erkennung (HTTP-Status-Auswertung)
- Zwei Modi: **Kommandozeile (CLI)** oder **lokale API**

---

## 🖥️ Nutzung

### 🔹 Modus 1: Kommandozeile (CLI)

```bash
java -jar PagePulse.jar https://zalando.com
```

→ Fragt ggf. nach einer URL  
→ Macht Screenshot und zeigt ihn im Standardbrowser (Base64)  
→ Ausgabe: Status, Screenshot vorhanden, etc.

---

### 🔹 Modus 2: Lokaler Server (API)

```bash
java -jar PagePulse.jar server
```

Dann im Browser aufrufen:

```
http://localhost:8080/view?url=https://example.com
```

→ Zeigt Screenshot im Browser direkt an  
→ Alternativ auch als JSON über `/check?url=...`

---

## 🧱 Projektstruktur (wichtigste Klassen)

```
src/main/java/com/feb17/pagePulse/
├── App.java                // Startpunkt (CLI & API)
├── ScreenshotService.java  // Screenshot + Base64
├── WebsiteChecker.java     // Erreichbarkeit prüfen
├── ScreenshotResult.java   // Datenmodell für Antwort
└── utils/
    ├── Driver.java         // Singleton WebDriver
    └── ConfigReader.java   // Lese config.properties
```

---

## 🔧 Build & Ausführen

Voraussetzungen:
- Java 17+
- Maven installiert
- Chrome + ChromeDriver im PATH

```bash
mvn clean package
java -jar target/PagePulse-1.0-SNAPSHOT-shaded.jar
```

---

## 📌 Beispielausgabe (CLI)

```json
{
  "url": "https://example.com",
  "reachable": true,
  "screenshot_saved": true,
  "screenshot_path": "screenshots/15042025_182544.png",
  "screenshot_base64": "iVBORw0KGgoAAAANSUhEUgAA..."
}
```

---

## 📝 Lizenz

Open Source – zu Lern- und Demonstrationszwecken.
