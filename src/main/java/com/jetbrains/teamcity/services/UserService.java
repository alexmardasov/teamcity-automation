package com.jetbrains.teamcity.services;


import com.jetbrains.teamcity.services.pojos.user.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.time.LocalDateTime;

public class UserService {
    public static AllUsersWithCountResponse getAllUsers() {
        return Specs.spec
                .when()
                .get("http://localhost:8112/app/rest/users")
                .getBody()
                .as(AllUsersWithCountResponse.class);
    }

    public static CreateNewUserResponse createNewUser(CreateNewUserRequest request) {
        return Specs.spec
                .when()
                .body(request).contentType(ContentType.JSON)
                .post("http://localhost:8112/app/rest/users")
                .getBody()
                .as(CreateNewUserResponse.class);
    }

    public static CreateTokenResponse provideAuthToken(String userName) {
        var request = CreateTokenRequest.builder()
                .name("token" + System.currentTimeMillis())
                .build();

        var response = Specs.spec
                .body(request)
                .post(RestAssured.baseURI + "/app/rest/users/username:"+ userName + "/tokens");
        response.then().statusCode(200);
        return response.getBody().as(CreateTokenResponse.class);
    }
}
