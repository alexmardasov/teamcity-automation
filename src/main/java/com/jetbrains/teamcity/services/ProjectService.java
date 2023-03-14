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
    private static final String PROJECTS_PATH = "/app/rest/projects";

    public static CreateProjectResponse createNewProject(CreateNewProjectRequest request) {
        log.info("Invoking 'Create a new project' method with a payload {}..", request);
        var response = RestSpecifications.getAcceptJsonRequestSpec()
                .when()
                .body(request).contentType(ContentType.JSON)
                .post(RestAssured.baseURI + PROJECTS_PATH);

        response.then().assertThat().statusCode(200).log().everything();
        return response.getBody().as(CreateProjectResponse.class);
    }

    public static void deleteProject(String projectId) {
        log.info("Invoking 'Delete a project' method with locator id:{}..", projectId);
        var response = RestSpecifications.getAcceptJsonRequestSpec()
                .when()
                .contentType(ContentType.JSON)
                .delete(RestAssured.baseURI + PROJECTS_PATH + "/id:" + projectId);

        response.then().assertThat().statusCode(204).log().everything();
    }

    public static CreateVCSRootResponse createVCSRoot(CreateVCSRootRequest request) {
        log.info("Invoking 'Create VCS root' method with a payload..\n{}", request);
        var response = RestSpecifications.getAcceptJsonRequestSpec()
                .when()
                .body(request).contentType(ContentType.JSON)
                .post(RestAssured.baseURI + "/app/rest/vcs-roots");

        response.then().assertThat().statusCode(200).log().everything();

        return response.getBody().as(CreateVCSRootResponse.class);

    }
}
