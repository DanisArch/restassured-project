package wrappers;

import dto.UserDTO;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ApiWrapper {

    public Response getUserById(int userId) {
        return given()
                .pathParam("id", userId)
                .when()
                .get("/profile/user/{id}")
                .thenReturn();
    }

    public Response createUser(UserDTO user) {
        return given()
                .body(user)
                .post("/users")
                .thenReturn();
    }

    // Add other methods for different API actions
}