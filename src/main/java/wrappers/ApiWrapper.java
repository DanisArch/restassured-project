package wrappers;

import dto.AuthenticationResponse;
import io.restassured.RestAssured;
import io.restassured.config.Config;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import filters.AuthenticationFilter;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.response.Response;

import java.net.http.HttpResponse;

import static io.restassured.RestAssured.given;

public class ApiWrapper {

    private final static int DEFAULT_STATUS_CODE_GET = 200;
    private final static int DEFAULT_STATUS_CODE_PATCH = 200;
    private final static int DEFAULT_STATUS_CODE_POST = 200;
    private final static int DEFAULT_STATUS_CODE_PUT = 200;
    private final static int DEFAULT_STATUS_CODE_DELETE = 204;
    private final static String TOKEN="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoyMzI3fSwiaWF0IjoxNzI3MzY2NTM5LCJleHAiOjE3Mjc3MjY1Mzl9.YmHPg0a5DUOpJmCG13K5U_L2emHpRIAs5X7F7uH6ClI";


    public static <T> T sendPostRequest(RequestSpecification requestSpec,
                                        String endpoint,
                                        T requestBody,
                                        int statusCode,
                                        Class<T> responseType) {
        return given()
                .filter(new AuthenticationFilter(TOKEN))
                .spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(endpoint)
                .then()
                .assertThat()
                .statusCode(statusCode)
                .contentType(ContentType.JSON)
                .log().ifValidationFails()
                .extract().as(responseType);
    }

    public static AuthenticationResponse sendPostRequest(
                                                         String endpoint,
                                                         String requestBody,
                                                         int statusCode) {
        return given()
                .filter(new AuthenticationFilter(TOKEN))
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(endpoint)
                .then()
                .assertThat()
                .statusCode(statusCode)
                .contentType(ContentType.JSON)
                .log().ifValidationFails()
                .extract().as(AuthenticationResponse.class);
    }

    public static <T> T sendPostRequest(String endpoint, T requestBody, Class<T> responseType) {

        return sendPostRequest(given(), endpoint, requestBody, DEFAULT_STATUS_CODE_POST, responseType);
    }

    public static <T> T sendPostRequest(String endpoint, T requestBody, int statusCode, Class<T> responseType) {

        return sendPostRequest(given(), endpoint, requestBody, statusCode, responseType);
    }

    public static <T> T sendPostRequest(RequestSpecification requestSpec, String endpoint, T requestBody, Class<T> responseType) {

        return sendPostRequest(requestSpec, endpoint, requestBody, DEFAULT_STATUS_CODE_POST, responseType);
    }

    public static <T> T sendPutRequest(RequestSpecification requestSpec,
                                       String endpoint,
                                       T requestBody,
                                       Class<T> responseType) {
        return given()
                .filter(new AuthenticationFilter(TOKEN))
                .spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put(endpoint)
                .then()
                .assertThat()
                .statusCode(DEFAULT_STATUS_CODE_PUT)
                .contentType(ContentType.JSON)
                .log().ifValidationFails()
                .extract().as(responseType);
    }

    public static ValidatableResponse sendPatchRequest(RequestSpecification requestSpec,
                                                       String nameCheckedField,
                                                       String valueCheckedField,
                                                       String callPath,
                                                       int statusCode) {
        return given()
                .filter(new AuthenticationFilter(TOKEN))
                .spec(requestSpec)
                .body("{ \"" + nameCheckedField + "\": \"" + valueCheckedField + "\" }")
                .contentType(ContentType.JSON)
                .when()
                .patch(callPath)
                .then()
                .statusCode(statusCode)
                .contentType(ContentType.JSON)
                .log().ifValidationFails()
                .body(nameCheckedField, equalTo(valueCheckedField));
    }

    public static ValidatableResponse sendPatchRequest(RequestSpecification requestSpec,
                                                       String nameCheckedField,
                                                       String valueCheckedField,
                                                       String callPath
    ) {
        return sendPatchRequest(requestSpec,
                nameCheckedField,
                valueCheckedField,
                callPath,
                DEFAULT_STATUS_CODE_PATCH);
    }

    public static ValidatableResponse sendGetRequest(RequestSpecification requestSpec,
                                                     String callPath,
                                                     int statusCode) {
        return given()
                .spec(requestSpec)
                .when()
                .get(callPath)
                .then()
                .statusCode(statusCode)
                .contentType(ContentType.JSON)
                .log().ifValidationFails();
    }

    public static Response sendGetListRequest(String callPath,
                                              int statusCode) {
        return given()
                .when()
                .get(callPath)
                .then()
                .statusCode(statusCode)
                .contentType(ContentType.JSON)
                .log().ifValidationFails()
                .extract().response();
    }

    public static ValidatableResponse sendGetRequest(String callPath, int statusCode) {
        return sendGetRequest(given(), callPath, statusCode);
    }

    public static ValidatableResponse sendGetRequest(RequestSpecification requestSpec, String callPath) {
        return sendGetRequest(requestSpec, callPath, DEFAULT_STATUS_CODE_GET);
    }

    public static ValidatableResponse sendGetRequest(String callPath) {
        return sendGetRequest(given(), callPath, DEFAULT_STATUS_CODE_GET);
    }

    public static ValidatableResponse deleteRequest(RequestSpecification requestSpec,
                                     String callPath,
                                     int statusCode) {
     return    given()
                .filter(new AuthenticationFilter(TOKEN))
                .spec(requestSpec)
                .when()
                .delete(callPath)
                .then()
                .log().ifValidationFails()
                .statusCode(statusCode);
    }

    public static ValidatableResponse deleteRequest(RequestSpecification requestSpec, String callPath) {
      return  deleteRequest(requestSpec, callPath, DEFAULT_STATUS_CODE_DELETE);
    }
}