package com.feb17.pagePulse.utils;


import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();




    static {
        try {
            FileReader file = new FileReader("src/main/resources/config.properties");
           properties.load(file);
           file.close();
        } catch (IOException e) {
            System.out.println("File not found "+e.getMessage());
        }
    }


    public static String getProperty(String key){
        return properties.getProperty(key);
    }

}
