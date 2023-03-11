package com.jetbrains.teamcity.services;

import com.jetbrains.teamcity.services.pojos.project.CreateNewProjectRequest;
import com.jetbrains.teamcity.services.pojos.project.CreateNewProjectResponse;
import io.restassured.http.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectService {

    private static final Logger log = LoggerFactory.getLogger(ProjectService.class);

    public static CreateNewProjectResponse createNewProject(CreateNewProjectRequest request) {
        log.info("Invoking http://localhost:8112/app/rest/projects endpoint..");
        var response = Specs.spec.when()
                .body(request).contentType(ContentType.JSON)
                .post("http://localhost:8112/app/rest/projects");

        response.then().assertThat().statusCode(200);
        return response.getBody().as(CreateNewProjectResponse.class);
    }

    public static void deleteProject(String projectId) {
        var response = Specs.spec.when()
                .contentType(ContentType.JSON)
                .delete("http://localhost:8112/app/rest/projects/id:" + projectId);

        response.then().assertThat().statusCode(204);
    }
}
