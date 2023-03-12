package com.jetbrains.teamcity.services;


import com.jetbrains.teamcity.services.pojos.user.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class UserService {
    public static AllUsersWithCountResponse getAllUsers() {
        return RestSpecifications.JSON_REQUEST_SPEC
                .when()
                .get(RestAssured.baseURI + "/app/rest/users")
                .getBody()
                .as(AllUsersWithCountResponse.class);
    }

    public static CreateNewUserResponse createNewUser(CreateUserRequest request) {
        return RestSpecifications.JSON_REQUEST_SPEC
                .when()
                .body(request).contentType(ContentType.JSON)
                .post(RestAssured.baseURI + "/app/rest/users")
                .getBody()
                .as(CreateNewUserResponse.class);
    }

    public static CreateTokenResponse provideAuthToken(String userName) {
        var request = CreateTokenRequest.builder()
                .name("token" + System.currentTimeMillis())
                .build();

        var response = RestSpecifications.JSON_REQUEST_SPEC
                .body(request)
                .post(RestAssured.baseURI + "/app/rest/users/username:"+ userName + "/tokens");
        response.then().statusCode(200);

        return response.getBody().as(CreateTokenResponse.class);
    }
}
