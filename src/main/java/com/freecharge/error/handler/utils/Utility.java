package com.freecharge.error.handler.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author fcsm18808 Shashank Mittal
 * @created Jan 25, 2019
 */
public class Utility {

	/**
	 * Returns a mapping from a properties file. The file should be a standard
	 * properties file having key value pairs per line.
	 *
	 * @param String
	 *            fileName
	 * @return Map<String, String>
	 * @throws IOException
	 *             If the file not found or some exception in reading the file
	 *             content.
	 */
	public static Map<String, String> getErrorMapping(File file) throws IOException {
		FileReader reader = new FileReader(file);
		Map<String, String> map = new HashMap<String, String>();
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
