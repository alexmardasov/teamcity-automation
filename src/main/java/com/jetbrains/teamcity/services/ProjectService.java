package com.jetbrains.teamcity.services;

import com.jetbrains.teamcity.services.pojos.project.CreateNewProjectRequest;
import com.jetbrains.teamcity.services.pojos.project.CreateProjectResponse;
import com.jetbrains.teamcity.services.pojos.vcsroots.CreateVCSRootRequest;
import com.jetbrains.teamcity.services.pojos.vcsroots.CreateVCSRootResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectService {

    private static final Logger log = LoggerFactory.getLogger(ProjectService.class);

    public static CreateProjectResponse createNewProject(CreateNewProjectRequest request) {
        log.info("Invoking http://localhost:8112/app/rest/projects endpoint..");
        var response = RestSpecifications.JSON_REQUEST_SPEC
                .when()
                .body(request).contentType(ContentType.JSON)
                .post(RestAssured.baseURI + "/app/rest/projects");

        response.then().assertThat().statusCode(200).log().everything();
        return response.getBody().as(CreateProjectResponse.class);
    }

    public static void deleteProject(String projectId) {
        var response = RestSpecifications.JSON_REQUEST_SPEC
                .when()
                .contentType(ContentType.JSON)
                .delete(RestAssured.baseURI + "/app/rest/projects/id:" + projectId);

        response.then().assertThat().statusCode(204);
    }

    public static CreateVCSRootResponse createVSCRoot(CreateVCSRootRequest request) {

        var response = RestSpecifications.JSON_REQUEST_SPEC
                .when()
                .body(request).contentType(ContentType.JSON)
                .post(RestAssured.baseURI + "/app/rest/vcs-roots");

        response.then().assertThat().statusCode(200).log().everything();

        return response.getBody().as(CreateVCSRootResponse.class);

    }
}
