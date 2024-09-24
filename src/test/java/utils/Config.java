package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

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

    public static int getID(String endPoint, String nameId) {
        String path = "[5]." + nameId;
        return
                given()
                        .when()
                        .get(getConfig("baseUrl") + getConfig(endPoint))
                        .then()
                        .statusCode(200)
                        .log().ifValidationFails()
                        .extract()
                        .jsonPath()
                        .getInt(path);
    }

    public static String getConfig(String key) {
        if(properties.isEmpty()){
            getProperties();
        }
        return properties.getProperty(key);
    }
}