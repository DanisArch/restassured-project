package tests;

import dto.Profile;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import utils.Config;
import utils.DataHelper;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.Config.getConfig;
import static wrappers.ApiWrapper.*;

public class ProfileTests {

    @Test
    public void getTestUserProfile() {
         sendGetRequest(
                given().headers("x-auth-token", Config.getConfig("token")),
                getConfig("baseURI")
                        + getConfig("objectProfileMePath"))
                .assertThat()
                .body("company", equalTo(Config.getConfig("company")))
                .body("location", equalTo(Config.getConfig("location")))
                .body("status", equalTo(Config.getConfig("status")))
                .body("profileId",equalTo(Config.getConfig("profilId"))
                );
    }

    @Test
    public void getTestUserByIdProfile() {
        sendGetRequest(
                getConfig("baseURI")
                        + getConfig("objectProfileUserIdPath")+getConfig("userId"))
                .assertThat()
                .body("id", equalTo(Integer.parseInt(Config.getConfig("profileId")))
                );
    }

    @Test
    public void getAllProfile() {
        Response response = sendGetListRequest(
                getConfig("baseURI") + getConfig("objectProfilePath"),
                200
                );

        List<Profile> profiles = response.jsonPath().getList("profiles",Profile.class);
    //    System.out.println("profiles = " + profiles.size());
        assertTrue(profiles.size() > 1000, "Количество профилей меньше 1000!");
    }

    @Test
    public void schemeTestUserProfileValidation() {

        sendGetRequest(
                given().headers("x-auth-token",Config.getConfig("token")),
                getConfig("baseURI") + getConfig("objectProfileMePath"))
                .assertThat()
                .body(matchesJsonSchemaInClasspath("profile-schema.json"));
    }

    @Test
    public void saveTestUserProfile() {
        String requestBody = "{ \"" + "company" + "\": \"" + Config.getConfig("company") + "\", " +
                                "\"" +"location" + "\": \"" + Config.getConfig("location") + "\"," +
                                "\"" +"year" + "\": " + Integer.parseInt(Config.getConfig("year")) + "," +
                                "\"" +"status" + "\": \"" + Config.getConfig("status") + "\"}";

        Profile newProfile= DataHelper.createProfile(Integer.parseInt(Config.getConfig("userId")));
        Profile actualProfileRequest = sendPostRequest(
                given().header("x-auth-token",Config.getConfig("token")).body(requestBody),
                Config.getConfig("baseURI") + Config.getConfig("objectProfilePath"),
                newProfile,
                Profile.class
        );
        assertEquals(actualProfileRequest, newProfile);
    }

    @Test
    public void deleteProfile() {

        ValidatableResponse responseMessage = deleteRequest(
                given().headers("x-auth-token",Config.getConfig("token")),
                getConfig("baseURI")
                        + getConfig("objectProfilePath")
        );
        assertEquals(responseMessage.toString(),"User deleted", "Не найден профиль пользователя, которого вы хотите " +
                "удалить");
    }

}
