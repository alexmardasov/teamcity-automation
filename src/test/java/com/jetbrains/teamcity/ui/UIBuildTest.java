package com.jetbrains.teamcity.ui;

import com.jetbrains.teamcity.platform.TeamcityBrowserContainer;
import com.jetbrains.teamcity.platform.TeamcityContainer;
import com.jetbrains.teamcity.services.BuildService;
import com.jetbrains.teamcity.services.ProjectService;
import com.jetbrains.teamcity.services.pojos.project.CreateNewProjectRequest;
import com.jetbrains.teamcity.services.pojos.project.ParentProject;
import com.jetbrains.teamcity.ui.pageobjects.BuildConfiguration;
import com.jetbrains.teamcity.ui.pageobjects.LoginPage;
import com.jetbrains.teamcity.ui.pageobjects.MainPage;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;

import static org.hamcrest.MatcherAssert.assertThat;

public class UIBuildTest {
    private static final String REPOSITORY_URL = "https://github.com/alexmardasov/test-rep.git";
    @Container
    private final TeamcityBrowserContainer<?> browser = TeamcityBrowserContainer.provideDriver();
    private MainPage mainPage;
    private final String projectName = "TestRep1234";
    private final String buildName = "Build1234";

    @BeforeEach
    public void init(TeamcityContainer teamcity) {
        browser.getWebDriver().get(teamcity.getWebUiUrl());

        mainPage = new LoginPage().userName("admin")
                .password("test")
                .login();
    }

    @AfterEach
    public void clean() {
        BuildService.deleteBuild(buildName);
        ProjectService.deleteProject(projectName);
    }

    /**
     * Ensure that new build can be created for a project.
     */
    @DisplayName("Teamcity UI - Create new build for a project")
    public void testCreateBuild() {

        var parentId = "_Root";
        var parentProject = ParentProject.withLocator(parentId);

        var createNewProjectRequest = CreateNewProjectRequest.builder()
                .name(projectName)
                .parentProject(parentProject)
                .build();

        var createProjectResponse = ProjectService.createNewProject(createNewProjectRequest);

        mainPage.openFromHeader(MainPage.HeaderItem.PROJECTS);

        var newProject = mainPage.leftSideBar()
                .createProject()
                .setUrl(REPOSITORY_URL)
                .proceedFirstScreen();

        var connectionSuccess = newProject.getConnectionMessage();

        newProject.setProjectName(projectName)
                .setBuildConfigurationName(buildName)
                .proceedSecondScreen();

        var buildConfiguration = new BuildConfiguration();

        var createSuccess = buildConfiguration.getSuccessMessage();

        assertThat(connectionSuccess, Matchers.equalTo(
                "✓ The connection to the VCS repository has been verified"));

        assertThat(createSuccess, Matchers.equalTo(
                "New project \"" + projectName + "\", build configuration \"" + buildName + "\"" +
                        " and VCS root \"" + REPOSITORY_URL + "#refs/heads/main\" have been successfully created."));
    }
}
