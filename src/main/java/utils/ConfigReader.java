package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try{
            InputStream inputStream = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            if (inputStream == null){
                throw new RuntimeException("config.properties not found");
            }
            properties.load(inputStream);
        } catch (Exception e){
            throw new RuntimeException("Fail to load config.properties", e);
        }
    }


    public static String get(String key){
        return properties.getProperty(key);
    }

    public static int getInt(String key){
        return Integer.parseInt(properties.getProperty(key));
    }

    public static boolean getBoolean(String key){
        return Boolean.parseBoolean(properties.getProperty(key));
    }
}
