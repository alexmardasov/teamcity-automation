package com.jetbrains.teamcity.services;


import com.jetbrains.teamcity.services.pojos.user.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private static final String USERS_PATH = "/app/rest/users";

    public static AllUsersWithCountResponse getAllUsers() {
        log.info("Invoking 'Get all users' method..");
        var response = RestSpecifications.JSON_REQUEST_SPEC
                .when()
                .get(RestAssured.baseURI + USERS_PATH);

        response.then().statusCode(200).log().everything();

        return response.getBody().as(AllUsersWithCountResponse.class);
    }

    public static CreateNewUserResponse createNewUser(CreateUserRequest request) {
        log.info("Invoking 'Create a new user' method with a payload {}..", request);
        var response = RestSpecifications.JSON_REQUEST_SPEC
                .when()
                .body(request).contentType(ContentType.JSON)
                .post(RestAssured.baseURI + USERS_PATH);

        response.then().statusCode(200).log().everything();

        return response.getBody().as(CreateNewUserResponse.class);
    }

    public static CreateTokenResponse provideAuthToken(String userName) {
        log.info("Invoking 'Provide Auth token' method for user with name {}..", userName);
        var request = CreateTokenRequest.builder()
                .name("token" + System.currentTimeMillis())
                .build();

        var response = RestSpecifications.JSON_REQUEST_SPEC
                .body(request)
                .post(RestAssured.baseURI + USERS_PATH + "/username:" + userName + "/tokens");

        response.then().statusCode(200).log().everything();

        return response.getBody().as(CreateTokenResponse.class);
    }
}
