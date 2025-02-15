package com.jetbrains.teamcity.ui;

import com.jetbrains.teamcity.platform.*;
import com.jetbrains.teamcity.services.BuildService;
import com.jetbrains.teamcity.services.ProjectService;
import com.jetbrains.teamcity.services.pojos.build.*;
import com.jetbrains.teamcity.services.pojos.common.Project;
import com.jetbrains.teamcity.services.pojos.common.Properties;
import com.jetbrains.teamcity.services.pojos.common.PropertyItem;
import com.jetbrains.teamcity.services.pojos.project.CreateNewProjectRequest;
import com.jetbrains.teamcity.services.pojos.project.ParentProject;
import com.jetbrains.teamcity.services.pojos.vcsroots.CreateVCSRootRequest;
import com.jetbrains.teamcity.services.utils.Deserializer;
import com.jetbrains.teamcity.ui.pageobjects.LoginPage;
import com.jetbrains.teamcity.ui.pageobjects.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@BrowserTest
public class UIBuildTest {
    private static final String REPOSITORY_URL = "https://github.com/alexmardasov/test-rep.git";
    private static final String BUILD_CONFIG_WITH_ARTIFACTS_TEMPLATE = "templates/build-config-with-artifacts.json";
    @Container
    private final TeamcityBrowserContainer<?> browser = TeamcityBrowserContainer.provideDriver();
    private final String projectName = "TestProject" + System.currentTimeMillis();
    private final String buildName = "Build" + System.currentTimeMillis();
    private final Deserializer deserializer = new Deserializer(this);
    private CreateBuildConfigurationResponse buildConfigResponse;
    private MainPage mainPage;

    @BeforeEach
    public void init(TeamcityContainer teamcity) {
        browser.getWebDriver().get(teamcity.getWebUiUrl());

        var parentId = "_Root";
        var parentProject = ParentProject.withLocator(parentId);

        var createNewProjectRequest = CreateNewProjectRequest.builder()
                .name(projectName)
                .parentProject(parentProject)
                .build();

        var projectResponse = ProjectService.createNewProject(createNewProjectRequest);

        var buildConfigRequest = deserializer.deserialize(
                BUILD_CONFIG_WITH_ARTIFACTS_TEMPLATE,
                CreateBuildConfigurationRequest.class);

        buildConfigRequest.setName(buildName);
        buildConfigRequest.setProject(new Project(projectResponse.getId()));

        buildConfigResponse = BuildService.createNewBuildConfiguration(buildConfigRequest);

        var branchProperty = new PropertyItem("branch", "main");
        var urlProperty = new PropertyItem("url", REPOSITORY_URL);
        var properties = new Properties(List.of(branchProperty, urlProperty));

        var vscRootRequest = CreateVCSRootRequest.builder()
                .vcsName("jetbrains.git")
                .name("TestVscRoot" + System.currentTimeMillis())
                .properties(properties)
                .project(new Project(projectName))
                .build();

        var vcsRootResponse = ProjectService.createVCSRoot(vscRootRequest);

        var addVcsToBuildRequest = AddVCSToBuildRequest.builder()
                .id(vcsRootResponse.getId())
                .vcsRoot(new VcsRoot(vcsRootResponse.getId()))
                .build();

        BuildService.addVCSToBuild(addVcsToBuildRequest, buildConfigResponse.getId());

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
     * Ensure that build can be run and is finished with 'Success' status.
     * Ensure that artifacts are produced after build execution and available on 'Artifacts' tab.
     */
    @Test
    @DisplayName("Teamcity UI - Run build that is successfully finished and produce some artifacts")
    public void testRunBuildThatProducesArtifacts() {

        mainPage.openFromHeader(MainPage.HeaderItem.PROJECTS);
        mainPage.leftSideBar()
                .searchProject(projectName)
                .selectBuild(buildConfigResponse.getId());

        mainPage.runBuild();

        var buildId = getBuildId();
        var status = mainPage.getBuildStatus(buildId);

        assertEquals("Success", status, "Status of the build is incorrect");

        mainPage.clickOnBuildStatus(buildId);
        mainPage.openArtifacts();
        var artifact = mainPage.getArtifactByFileName("README.md");

        assertTrue(artifact.isDisplayed());
    }


    private String getBuildId() {
        //Wait until the build is finished
        String buildId = "";
        var startTime = System.currentTimeMillis();

        while (buildId.isEmpty() || System.currentTimeMillis() - startTime < Timeouts.BUILD_SUCCESS_TIMEOUT) {
            try {
                buildId = BuildService.getAllBuilds()
                        .getBuild().stream()
                        .filter(build -> build.getBuildTypeId().equals(buildConfigResponse.getId()))
                        .map(build -> String.valueOf(build.getId()))
                        .collect(Collectors.toList())
                        .get(0);
            } catch (Throwable e) {
                System.err.println(e);
                Awaits.doSleep();
            }
        }
        return buildId;
    }
}
