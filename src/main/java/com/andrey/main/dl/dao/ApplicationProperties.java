package com.andrey.main.dl.dao;


import java.io.*;
import java.util.Properties;

public class ApplicationProperties {
    //    private static final String PATH_TO_FILE = "/conf/db.properties";
    private static final String PATH_TO_FILE = new GetPath().getAbsolutePathFile("/configuration/db.properties");
    public static Properties properties = readProperties(PATH_TO_FILE);


    public static void savePropertiesToFile() {
        savePropertiesToFile(PATH_TO_FILE);
    }

    public static void savePropertiesToFile(String fileName) {
        OutputStream output = null;
        try {
            output = new FileOutputStream(fileName);
            properties.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void writeProperty(String key, String value) {
        properties.setProperty(key, value);
    }

    public static String readProperty(String key) {
        return properties.getProperty(key);
    }

    public static Properties readProperties(String fileName) {
        Properties properties = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream(fileName);

            properties.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }

    public static class GetPath {
        public String getAbsolutePathFile(String pathInProject){
            return getClass().getResource(pathInProject).getPath();
        }
    }
}
