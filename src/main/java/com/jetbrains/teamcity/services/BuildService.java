package com.jetbrains.teamcity.services;

import com.jetbrains.teamcity.services.pojos.build.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuildService {

    private static final Logger log = LoggerFactory.getLogger(BuildService.class);

    public static CreateBuildConfigurationResponse createNewBuildConfiguration(CreateBuildConfigurationRequest request) {
        log.info("Invoking http://localhost:8112/app/rest/projects endpoint..");

        var response = RestSpecifications.JSON_REQUEST_SPEC
                .when()
                .body(request).contentType(ContentType.JSON)
                .post(RestAssured.baseURI + "/app/rest/buildTypes");

        response.then().assertThat().statusCode(200).log().everything();
        return response.getBody().as(CreateBuildConfigurationResponse.class);
    }

    public static String runBuild(StartBuildRequest request) {
        var response = RestSpecifications.JSON_REQUEST_SPEC
                .when()
                .body(request).contentType(ContentType.JSON)
                .post(RestAssured.baseURI + "/app/rest/buildQueue");

        response.then().assertThat().statusCode(200).log().everything();

        return response.getBody().asPrettyString();
    }

    public static String addVCSToBuild(AddVCSToBuildRequest request, String buildId) {

        var response = RestSpecifications.JSON_REQUEST_SPEC
                .when()
                .body(request).contentType(ContentType.JSON)
                .post(RestAssured.baseURI + "/app/rest/buildTypes/id:" + buildId + "/vcs-root-entries");

        response.then().assertThat().statusCode(200).log().everything();
        return response.getBody().asPrettyString();
    }

    public static String getBuildResults(String buildId) {
        return "";
    }

    public static void deleteBuild(String buildName) {
        RestSpecifications.JSON_REQUEST_SPEC.when()
                .delete(RestAssured.baseURI + "/app/rest/buildTypes/name:" + buildName)
                .getBody().asPrettyString();
    }
}
