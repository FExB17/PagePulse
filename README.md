
# 📸 PagePulse – Website Screenshot Tool

**PagePulse** ist ein flexibles Tool zur automatischen Überprüfung von Websites und Erstellung von Screenshots – entweder über die Kommandozeile, per Web-API oder mit einer einfachen Benutzeroberfläche (GUI).  
Es kann als `.exe` verwendet werden und benötigt **keine Java-Installation**, wenn die JRE mitgeliefert wird.

---

## 🔧 Funktionen

- 🌐 Prüft, ob eine Website erreichbar ist (`GET`-Request)
- 📷 Erstellt automatisch einen Screenshot mit **Selenium**
- 💾 Speichert das Bild lokal & gibt es als **Base64** zurück (API)
- 🖥️ Startbar über:
  - **GUI mit Suchfeld**
  - **Kommandozeile (CLI)**
  - **Webserver (API via `/check?url=...`)**

---

## 🗃️ Projektstruktur

```
PagePulse/
├─ PagePulse.exe              → Start der App (GUI oder CLI)
├─ guiApp.jar / .class        → GUI-Modus (Swing)
├─ pagepulse-all.jar          → Fat-JAR mit CLI und API
├─ jre/                       → eingebettete Java-Laufzeitumgebung (JRE 17)
├─ resources/config.properties → Screenshot-Speicherort
```

---

## 🚀 Verwendung

### 📌 1. GUI-Modus

Starte `PagePulse.exe`, um eine einfache grafische Oberfläche zu öffnen:  
🔍 Gib eine URL ein → klicke „Search“ → Screenshot wird angezeigt.

---

### 💻 2. CLI-Modus

```bash
PagePulse.exe https://example.com
```

- Erstellt einen Screenshot der angegebenen Website.
- Öffnet diesen im Standard-Browser.
- Der Pfad des Screenshots wird in der Konsole ausgegeben.

Ohne Argument wirst du nach einer URL gefragt.

---

### 🌐 3. Webserver-Modus (API)

```bash
PagePulse.exe server
```

Dann im Browser oder per Tool wie Postman:

```
GET http://localhost:8080/check?url=https://example.com
```

Antwort:
```json
{
  "url": "https://example.com",
  "reachable": true,
  "screenshot_saved": true,
  "screenshot_path": "...",
  "screenshot_base64": "..."
}
```

---

## ⚙️ Konfiguration

**`config.properties`**:

```properties
screenshotPath=screenshots/
```

Pfad, unter dem Screenshots gespeichert werden.

---

## 🪛 Technische Anforderungen

- Selenium (headless oder sichtbar)
- ChromeDriver (muss zur Chrome-Version passen)
- JDK/JRE 17  
→ Wird bei der `.exe` bereits mitgeliefert (Ordner `jre/`)

---

## 📦 Distribution als `.exe`

Die Datei `PagePulse.exe` wurde mit **Launch4j** erstellt und verwendet eine **eingebettete JRE**, sodass **keine Java-Installation** erforderlich ist.

---

## ❗ Hinweise

- **ChromeDriver** muss zur installierten Chrome-Version passen.
- Manche Seiten erkennen automatisierte Zugriffe – dies kann den Screenshot verhindern.
- Im CLI-Modus wird die Seite im Browser geöffnet (`Desktop.browse()`).
