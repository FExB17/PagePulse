package com.feb17.pagePulse;


import com.feb17.pagePulse.utils.ConfigReader;
import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;

import java.awt.*;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;



public class App {
    //TODO muss noch korrigiert werden
    //TODO es werden grenzenlos screenshots aufgezeichnet -> muss limitiert werden
    //TODO doppelte deklarationen und ctx.json(Map.of) aufheben

    // nimmt die url von der Kommandozeile im Befehl oder von der Eingabe
    public static void runCliMode(String[] args) {
        String url = "";
        if (args.length > 0) {
            url = args[0];
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the URL of the website you want to check: ");
            url = scanner.nextLine();
        }
        //TODO sagt derzeit nur aus, dass es kein Error gibt sollte besser überprüft werden
        WebsiteChecker checker = new WebsiteChecker();
        boolean reachable = checker.isWebsiteReachable(url);

        ScreenshotService ss = new ScreenshotService();
        String path = ConfigReader.getProperty("screenshotPath") + LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss")) + ".png";
        boolean success = ss.captureScreenshot(url, path);

        String viewURL = "http://localhost:8080/view?url="+ URLEncoder.encode(url, StandardCharsets.UTF_8);
        try{
            Desktop.getDesktop().browse(new URI(viewURL));
            System.out.println("Opening screenshot in Browser...");
        }catch (Exception e){
            System.out.println("Error while opening the browser: " + e.getMessage());
        }

        System.out.println("Screenshot saved: " + success);

    }

    public static void startApiServer() {
        Javalin app = Javalin.create(config -> {
            config.jsonMapper(new JavalinJackson());
        }).start(8080);

        app.get("/check", ctx -> {
            String url = ctx.queryParam("url");
            if (url == null || url.isBlank()) {
                ctx.status(400).result("Missing URL parameter.");
                return;
            }
            WebsiteChecker checker = new WebsiteChecker();
            boolean reachable = checker.isWebsiteReachable(url);

            ScreenshotService ss = new ScreenshotService();
            String path = ConfigReader.getProperty("screenshotPath") + LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss")) + ".png";
            ScreenshotResult result = ss.captureScreenshotBase64(url, path);


            ctx.json(Map.of(
                    "url", url,
                    "reachable", reachable,
                    "screenshot_saved", result.success,
                    "screenshot_path", result.path,
                    "screenshot_base64", result.base64
            ));
        });

    }

    public static void main(String[] args) {
        // In IntelliJ kann man in den Konfigurationen automatisch einen Parameter übergeben
        // ansonsten muss man separat die app bauen (bei Java, aber nicht bei go) und dann mit java -jar "dateiname" aufrufen
        // mit server als argument öffnet sich der Server und ohne Argument wird die CLI gestartet

        if (args.length > 0 && args[0].equalsIgnoreCase("server")) {
            startApiServer();
        } else {
            runCliMode(args);
        }

    }
}

