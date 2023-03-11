package com.jetbrains.teamcity.api;

import com.jetbrains.teamcity.services.ProjectService;
import com.jetbrains.teamcity.services.pojos.project.CreateNewProjectRequest;
import com.jetbrains.teamcity.services.pojos.project.CreateNewProjectResponseMatcher;
import com.jetbrains.teamcity.services.pojos.project.ParentProject;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class ProjectTest {

    @Test
    public void testCreateNewProject() {
        var parentId = "_Root";
        var projectName = "TestProj" + System.currentTimeMillis();
        var parentProject = ParentProject.withLocator(parentId);

        var createNewProjectRequest = CreateNewProjectRequest.builder()
                .name(projectName)
                .parentProject(parentProject)
                .build();

        var createNewProjectResponse = ProjectService.createNewProject(createNewProjectRequest);

        var createNewProjectResponseMatcher = new CreateNewProjectResponseMatcher()
                .withName(projectName)
                .withParentProjectId(parentId)
                .withHref("/app/rest/projects/id:" + projectName)
                .withWebUrl("http://localhost:8111/project.html?projectId=" + projectName);

        assertThat(createNewProjectResponse, createNewProjectResponseMatcher);

        ProjectService.deleteProject(createNewProjectResponse.getId());
    }
}
