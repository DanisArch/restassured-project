package tests;

import dto.AuthenticationResponse;
import dto.User;
import filters.AuthenticationFilter;
import io.restassured.response.Response;
import utils.Config;
import utils.DataHelper;
import wrappers.ApiWrapper;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static utils.Config.getConfig;

import static wrappers.ApiWrapper.sendGetRequest;
import static wrappers.ApiWrapper.sendPostRequest;

public class UserTests extends ApiBaseTest {


    @Test
    public void RegisterNewUser() {
        User newUser = DataHelper.createUser();

        User actualnewUserToken =
                sendPostRequest(
                        getConfig("baseURI")
                                + getConfig("endPointRegisterUsers"),
                        newUser,
                        User.class
                );
        System.out.println(actualnewUserToken);
 //       assertNotNull(actualnewUserToken.getToken());
    }

    @Test
    public void creationTestUser() {

        User testUser = DataHelper.createTestUser();

        User actualTestUserMsg =
                sendPostRequest(
                        getConfig("baseURI")
                                + getConfig("endPointRegisterUsers"),
                        testUser,
                        User.class
                );

        assertEquals(actualTestUserMsg, Config.getConfig("CreateTestUserMsg"));
   /*     String token = ApiWrapper.sendPatchRequest().jsonPath().getString("token");
        String authToken = token;*/
    }

    @Test
    public void getTestUser() {
        sendGetRequest(
                        given().headers("x-auth-token",Config.getConfig("token")),
                getConfig("baseURI")
                        + getConfig("endPointAuthPath"))
                .assertThat()
                .body("name", equalTo(Config.getConfig("name")))
                .body("email", equalTo(Config.getConfig("email"))
                );
    }

    @Test
    public void LoginTestUser() {
        String requestBody = "{ \"" + "email" + "\": \"" + Config.getConfig("email") + "\", \"" +
                "password" + "\": \"" + Config.getConfig("password") + "\" }";

        AuthenticationResponse responseAuth = sendPostRequest(
                Config.getConfig("baseURI") + Config.getConfig("endPointAuthPath"),
                requestBody,
                200
        );

        assertNotNull(responseAuth.getToken());
        //TODO: Создать environment, где будут хранится переменные token
/*      // И добавить тест по сравнению полученного токена с тем, что у нас есть
        String expectedToken = Config.getConfig("token");
        String actualToken = responseAuth.getToken();  // Предполагается, что есть метод getToken в классе AuthResponse
        assertEquals(expectedToken, actualToken, "Токены не совпадают!");*/
    }

    @Test
    public void schemeTestUserValidation() {
 //       String testUser = getId("endPointUsers", "id");
        sendGetRequest(
                given().headers("x-auth-token",Config.getConfig("token")),
                getConfig("baseURI")
                        + getConfig("endPointAuthPath"))
                .assertThat()
                .body(matchesJsonSchemaInClasspath("user-schema.json"));
    }
}
