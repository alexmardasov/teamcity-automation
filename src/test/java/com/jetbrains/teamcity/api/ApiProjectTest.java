package com.jetbrains.teamcity.api;

import com.jetbrains.teamcity.services.ProjectService;
import com.jetbrains.teamcity.services.pojos.common.Project;
import com.jetbrains.teamcity.services.pojos.common.Properties;
import com.jetbrains.teamcity.services.pojos.common.PropertyItem;
import com.jetbrains.teamcity.services.pojos.project.*;
import com.jetbrains.teamcity.services.pojos.vcsroots.CreateVCSRootRequest;
import com.jetbrains.teamcity.services.pojos.vcsroots.CreateVCSRootResponseMatcher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class ApiProjectTest {

    private CreateProjectResponse createProjectResponse;
    private CreateNewProjectRequest createNewProjectRequest;
    private String projectName;

    @BeforeEach
    public void init() {
        var parentId = "_Root";
        projectName = "TestProj" + System.currentTimeMillis();
        var parentProject = ParentProject.withLocator(parentId);

        createNewProjectRequest = CreateNewProjectRequest.builder()
                .name(projectName)
                .parentProject(parentProject)
                .build();

        createProjectResponse = ProjectService.createNewProject(createNewProjectRequest);
    }

    @AfterEach
    public void cleanUp() {
        ProjectService.deleteProject(createProjectResponse.getId());
    }

    /**
     * Ensure that new project can be created
     */
    @Test
    @DisplayName("Teamcity API - Create new project")
    public void testCreateNewProject() {

        var createNewProjectResponseMatcher = new CreateProjectResponseMatcher()
                .withName(createNewProjectRequest.getName())
                .withParentProjectId("_Root")
                .withHref("/app/rest/projects/id:" + projectName)
                .withWebUrl("http://localhost:8111/project.html?projectId=" + projectName);

        assertThat(createProjectResponse, createNewProjectResponseMatcher);
    }

    /**
     * Ensure that vcs root can be created for some project
     */
    @Test
    @DisplayName("Teamcity API - Create new VCS root")
    public void testCreateVSCRoot() {

        var branchProperty = new PropertyItem("branch", "main");
        var urlProperty = new PropertyItem("url", "https://github.com/alexmardasov/test-rep.git");
        var properties = new Properties(List.of(branchProperty, urlProperty));

        var vscRootRequest = CreateVCSRootRequest.builder()
                .vcsName("jetbrains.git")
                .name("TestVscRoot" + System.currentTimeMillis())
                .properties(properties)
                .project(new Project(projectName))
                .build();

        var vcsRootResponse = ProjectService.createVCSRoot(vscRootRequest);
        var vcsRootResponseMatcher = new CreateVCSRootResponseMatcher()
                .withVcsName("jetbrains.git")
                .withId(projectName + "_" + vscRootRequest.getName())
                .withName(vscRootRequest.getName());

        assertThat(vcsRootResponse, vcsRootResponseMatcher);
    }


}
