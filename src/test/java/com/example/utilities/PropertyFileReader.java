package com.example.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileReader {
    private static Properties configFile;

    public static void loadProperties(String propertyPath) {
        try {
            String path = System.getProperty("user.dir") + propertyPath;
            FileInputStream input = new FileInputStream(path);
            configFile = new Properties();
            configFile.load(input);
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file!");
        }
    }

    public static String getString(String keyName) {
        return configFile.getProperty(keyName);
    }

    public static int getInt(String keyName) {
        return Integer.parseInt(configFile.getProperty(keyName));
    }

    public static long getLong(String keyName) {
        return Long.parseLong(configFile.getProperty(keyName));
    }

    public static double getDouble(String keyName) {
        return Double.parseDouble(configFile.getProperty(keyName));
    }

    public static boolean getBoolean(String keyName) {
        return Boolean.parseBoolean(configFile.getProperty(keyName));
    }

    public static String getPropertyValue(String propertyPath, String keyName) {
        try {
            String path = System.getProperty("user.dir") + "/Configuration.properties";
            String path2 = System.getProperty("user.dir") + "/" + propertyPath;
            FileInputStream input = new FileInputStream(path2);
            configFile = new Properties();
            configFile.load(input);
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file!");
        }
        return configFile.getProperty(keyName);
    }
}
