package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeEach;
import utils.Config;

public class ApiBaseTest {

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = Config.getConfig("baseURI");
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(),new AllureRestAssured());
    }
}

