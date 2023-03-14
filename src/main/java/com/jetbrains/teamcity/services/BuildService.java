package com.jetbrains.teamcity.services;

import com.jetbrains.teamcity.services.pojos.build.*;
import com.jetbrains.teamcity.services.pojos.user.AllUsersWithCountResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuildService {

    private static final Logger log = LoggerFactory.getLogger(BuildService.class);
    private static final String BUILD_TYPES_PATH = "/app/rest/buildTypes";

    public static CreateBuildConfigurationResponse createNewBuildConfiguration(CreateBuildConfigurationRequest request) {
        log.info("Invoking 'Create a new build configuration' method with a payload {}..", request);
        var response = RestSpecifications.getAcceptJsonRequestSpec()
                .when()
                .body(request).contentType(ContentType.JSON)
                .post(RestAssured.baseURI + BUILD_TYPES_PATH);

        response.then().assertThat().statusCode(200).log().everything();
        return response.getBody().as(CreateBuildConfigurationResponse.class);
    }

    public static StartBuildResponse runBuild(StartBuildRequest request) {
        log.info("Invoking 'Create run build' method with a payload {}..", request);
        var response = RestSpecifications.getAcceptJsonRequestSpec()
                .when()
                .body(request).contentType(ContentType.JSON)
                .post(RestAssured.baseURI + "/app/rest/buildQueue");

        response.then().assertThat().statusCode(200).log().everything();

        return response.getBody().as(StartBuildResponse.class);
    }

    public static String addVCSToBuild(AddVCSToBuildRequest request, String buildId) {
        log.info("Invoking 'Add VCS to selected build' method with a payload {}, for buildID:{}..", request, buildId);

        var response = RestSpecifications.getAcceptJsonRequestSpec()
                .when()
                .body(request).contentType(ContentType.JSON)
                .post(RestAssured.baseURI + BUILD_TYPES_PATH + "/id:" + buildId + "/vcs-root-entries");

        response.then().assertThat().statusCode(200).log().everything();
        return response.getBody().asPrettyString();
    }

    public static void deleteBuild(String buildName) {
        log.info("Invoking 'Delete build' method for build with name:{}..", buildName);
        RestSpecifications.getAcceptJsonRequestSpec().when()
                .delete(RestAssured.baseURI + BUILD_TYPES_PATH + "/name:" + buildName)
                .getBody().asPrettyString();
    }

    public static GetAllBuildsResponse getAllBuilds() {
        log.info("Invoking 'Get all builds' method..");
        var response = RestSpecifications.getAcceptJsonRequestSpec()
                .when()
                .get(RestAssured.baseURI + "/app/rest/builds");

        response.then().assertThat().statusCode(200).log().everything();

        return response.getBody().as(GetAllBuildsResponse.class);
    }
}
