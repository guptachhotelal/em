package com.em.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Helper {

    public static final Properties loadProperties(String propertyFileName) {
        Properties properties = new Properties();
        try (InputStream is = Helper.class.getResourceAsStream("/" + propertyFileName)) {
            properties.load(is);
        } catch (IOException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return properties;
    }

    public static final Map<String, String> convertToMap(Properties prop) {
        Map<String, String> map = new TreeMap<>();
        prop.stringPropertyNames().forEach((key) -> {
            map.put(key, prop.getProperty(key));
        });
        return map;
    }

}
