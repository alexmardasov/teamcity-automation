package com.jetbrains.teamcity.services;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Specs {
    private static String TOKEN = "eyJ0eXAiOiAiVENWMiJ9.azU5dHVGd1RpZUFuSVUtNUZIYm5YY1ZTTmlv.OWUwNDZhMmQtOWUyOS00ZmY1LWFiZWQtNmVjYjI4ZTVjOTk5";
    public static final RequestSpecification spec = RestAssured.given()
            .accept(ContentType.JSON)
            .header("Authorization", "Bearer " + TOKEN);

    static {
        RestAssured.baseURI = "http://localhost:8112";
    }
}
