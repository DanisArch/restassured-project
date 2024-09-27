package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Config {
    private static Properties properties = new Properties();

    @BeforeAll
    public static void getProperties() {

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

    @AfterEach
    public void tearDown() {
        RestAssured.reset();
    }
}