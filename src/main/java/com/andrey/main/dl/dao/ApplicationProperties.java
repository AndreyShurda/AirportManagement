package com.andrey.main.dl.dao;


import java.io.*;
import java.util.Properties;

public class ApplicationProperties {
    public static Properties properties = readProperties(getAbsolutePathToFile());
    private static final String PATH_TO_FILE = "/conf/db.properties";
//    public static String absolutePathToFile = new Configurations().getAbsolutePathToFile(PATH_TO_FILE);;
//    public static Properties properties = readProperties(getAbsolutePathToFile());
//    private String pathToFile = getClass().getClassLoader().getResource("configuration/db.properties").getFile();


    private static String getAbsolutePathToFile() {
        File currentDirectory = new File(new File("").getAbsolutePath());
        String absolutePath = currentDirectory.getAbsolutePath();
        System.out.println(absolutePath);
        return absolutePath + PATH_TO_FILE;
//        return new Configurations().getAbsolutePathToFile(PATH_TO_FILE);
    }

//    private String getAbsolutePathToFile(String filePath){
//        return getClass().getClassLoader().getResource(filePath).getFile();
//    }

    public static void savePropertiesToFile() {
        savePropertiesToFile(getAbsolutePathToFile());
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
}
