package com.feb17.pagePulse;




import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebsiteChecker {
    public boolean isWebsiteReachable(String url) {
        try {
            URL checkURL = new URL(url);
            // damit ich zugang zu den status codes habe
            // überprüft nicht mit selenium, sondern sendet nur eine Anfrage, die natürlich als Bot erkannt wird
            HttpURLConnection connection = (HttpURLConnection) checkURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);

            int statusCode = connection.getResponseCode();
            System.out.println("Statuscode: " + statusCode);

            return statusCode >= 200 && statusCode < 400;
        } catch (IOException e) {
            System.out.println("Error while checking the website: " + e.getMessage());
            return false;
        }
    }
}

