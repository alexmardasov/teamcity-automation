package com.jetbrains.teamcity.services;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestSpecifications {
    private static final String TOKEN = "eyJ0eXAiOiAiVENWMiJ9.azU5dHVGd1RpZUFuSVUtNUZIYm5YY1ZTTmlv.OWUwNDZhMmQtOWUyOS00ZmY1LWFiZWQtNmVjYjI4ZTVjOTk5";

    static {
        RestAssured.baseURI = "http://localhost:8112";
    }

    public static RequestSpecification getAcceptJsonRequestSpec() {
        return RestAssured.given()
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + TOKEN);
    }

    public static RequestSpecification getAcceptTextRequestSpec() {
        return RestAssured.given()
                .accept(ContentType.TEXT)
                .header("Authorization", "Bearer " + TOKEN);
    }

}
