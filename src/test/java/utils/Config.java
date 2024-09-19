package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Properties properties = new Properties();

    private static void getProperties() {

        try {
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения конфигурационного файла 'config.properties'", e);
        }
    }

    public static String getConfig(String key) {
        if(properties.isEmpty()){
            getProperties();
        }
        return properties.getProperty(key);
    }
}