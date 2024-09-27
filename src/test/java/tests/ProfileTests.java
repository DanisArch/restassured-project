package tests;

import org.junit.jupiter.api.Test;
import utils.Config;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utils.Config.getConfig;
import static wrappers.ApiWrapper.sendGetRequest;

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


}
