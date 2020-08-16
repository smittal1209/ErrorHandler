package com.services.error.handler.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author shashankmittal
 * @created 25/02/20
 */
public class Utility {

    /**
     * Returns a mapping from a properties file. The file should be a standard
     * properties file having key value pairs per line.
     *
     * @param file File object in which error code mappings are defined
     * @return Map<String, String> A map of error code vs. display message.
     * @throws IOException If the file not found or some exception in reading the file
     *                     content.
     */
    public static Map<String, String> getErrorMapping(File file) throws IOException {
        FileReader reader = new FileReader(file);
        Map<String, String> map = new HashMap<>();
        Properties properties = new Properties();
        properties.load(reader);

        for (final String name : properties.stringPropertyNames()) {
            map.put(name, properties.getProperty(name));
        }
        return map;
    }

    public static boolean isNullOrBlank(String s) {
        return !(s != null && s.trim().length() > 0);
    }
}
