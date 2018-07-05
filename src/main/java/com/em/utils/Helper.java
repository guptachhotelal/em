package com.em.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Helper {

    private static final Logger LOGGER = LogManager.getLogger(Helper.class.getName());

    private Helper() {
    }

    public static final Properties loadProperties(String propertyFileName) {
        Properties properties = new Properties();
        try (InputStream is = Helper.class.getResourceAsStream("/" + propertyFileName)) {
            properties.load(is);
        } catch (IOException ex) {
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
