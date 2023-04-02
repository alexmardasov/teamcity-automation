package com.jetbrains.teamcity.ui;

import com.jetbrains.teamcity.platform.BrowserTest;
import com.jetbrains.teamcity.platform.TeamcityBrowserContainer;
import com.jetbrains.teamcity.platform.TeamcityContainer;
import com.jetbrains.teamcity.services.BuildService;
import com.jetbrains.teamcity.services.ProjectService;
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


@BrowserTest
public class UIProjectTest {

    private static final String REPOSITORY_URL = "https://github.com/alexmardasov/test-rep.git";
    @Container
    private final TeamcityBrowserContainer<?> browser = TeamcityBrowserContainer.provideDriver();
    private MainPage mainPage;
    private final String projectName = "TestRep" + System.currentTimeMillis();
    private final String buildName = "Build" + System.currentTimeMillis();

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
     * Ensure that new project can be successfully created.
     * Message 'New project "project_name", build configuration "build_conf_name" and VCS root "vcs-root"
     * have been successfully created.' appears.
     */
    @Test
    @DisplayName("Teamcity UI - Create new project")
    public void testCreateProjectWithBuildConfiguration() {

        mainPage.openFromHeader(MainPage.HeaderItem.PROJECTS);

        var newProject = mainPage.leftSideBar()
                .createProject()
                .setUrl(REPOSITORY_URL)
                .proceed();

        var connectionSuccess = newProject.getConnectionMessage();

        assertThat(connectionSuccess, Matchers.equalTo(
                "✓ The connection to the VCS repository has been verified"));

        newProject.setProjectName(projectName)
                .setBuildConfigurationName(buildName)
                .proceed();

        var buildConfiguration = new BuildConfiguration();

        var createSuccess = buildConfiguration.getSuccessMessage();

        assertThat(createSuccess, Matchers.equalTo(
                "New project \"" + projectName + "\", build configuration \"" + buildName + "\"" +
                        " and VCS root \"" + REPOSITORY_URL + "#refs/heads/main\" have been successfully created."));
    }
}
