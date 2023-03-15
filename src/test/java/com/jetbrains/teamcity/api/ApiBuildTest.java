package com.jetbrains.teamcity.api;

import com.jetbrains.teamcity.services.BuildService;
import com.jetbrains.teamcity.services.ProjectService;
import com.jetbrains.teamcity.services.pojos.build.*;
import com.jetbrains.teamcity.services.pojos.common.Project;
import com.jetbrains.teamcity.services.pojos.common.Properties;
import com.jetbrains.teamcity.services.pojos.common.PropertyItem;
import com.jetbrains.teamcity.services.pojos.common.PropertyItemMatcher;
import com.jetbrains.teamcity.services.pojos.project.CreateNewProjectRequest;
import com.jetbrains.teamcity.services.pojos.project.CreateProjectResponse;
import com.jetbrains.teamcity.services.pojos.project.ParentProject;
import com.jetbrains.teamcity.services.pojos.vcsroots.CreateVCSRootRequest;
import com.jetbrains.teamcity.services.utils.Deserializer;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class ApiBuildTest {

    private static final String BUILD_CONFIG_WITH_STEPS_PAYLOAD = "templates/build-config-with-steps.json";
    private static final String BUILD_CONFIG_WITHOUT_STEPS_PAYLOAD = "templates/build-config-without-steps.json";
    private CreateBuildConfigurationRequest buildConfigRequest;
    private CreateProjectResponse createdProjectResponse;
    private CreateBuildConfigurationResponse buildConfigResponse;
    private final Deserializer deserializer = new Deserializer(this);
    private String projectName;

    @BeforeEach
    public void onStart() {
        projectName = "TestProj" + System.currentTimeMillis();

        var parentId = "_Root";
        var parentProject = ParentProject.withLocator(parentId);

        var createNewProjectRequest = CreateNewProjectRequest.builder()
                .name(projectName)
                .parentProject(parentProject)
                .build();

        createdProjectResponse = ProjectService.createNewProject(createNewProjectRequest);
    }

    @AfterEach
    public void cleanUp() {
        BuildService.deleteBuild(buildConfigResponse.getName());
        ProjectService.deleteProject(createdProjectResponse.getId());
    }

    /**
     * Ensure that it is possible to create new build configuration
     */
    @Test
    @DisplayName("Teamcity API - Create new build configuration")
    public void testCreateNewBuildConfiguration() {
        // For some big json payloads I decided to deserialize them using Jackson Object mapper
        // The code looks clearer and understandable
        buildConfigRequest = deserializer.deserialize(
                BUILD_CONFIG_WITH_STEPS_PAYLOAD,
                CreateBuildConfigurationRequest.class);

        buildConfigRequest.setName("test_build" + System.currentTimeMillis());
        buildConfigRequest.setProject(new Project(createdProjectResponse.getId()));

        buildConfigResponse = BuildService.createNewBuildConfiguration(buildConfigRequest);

        var buildConfigMatcher = new CreateBuildConfigurationResponseMatcher()
                .withName(buildConfigRequest.getName())
                .withProjectId(buildConfigRequest.getProject().getId());

        assertThat(buildConfigResponse, buildConfigMatcher);

        var requestParamsProperties = buildConfigRequest.getParameters().getProperty();
        var responseParamsProperties = buildConfigResponse.getParameters().getProperty();

        assertThat(responseParamsProperties.size(), Matchers.equalTo(1));

        var paramsPropertiesMatcher = new PropertyItemMatcher()
                .withName(requestParamsProperties.get(0).getName())
                .withValue(requestParamsProperties.get(0).getValue());

        assertThat(responseParamsProperties.get(0), paramsPropertiesMatcher);

        var requestStepItem = buildConfigRequest.getSteps().getStep();
        var responseStepItem = buildConfigResponse.getSteps().getStep();

        assertThat(responseStepItem.size(), Matchers.equalTo(1));

        var requestStep = requestStepItem.get(0);
        var stepItemMatcher = new StepItemMatcher()
                .withName(requestStep.getName())
                .withType(requestStep.getType());

        assertThat(responseStepItem.get(0), stepItemMatcher);

        var requestStepProperty = requestStep.getProperties().getProperty();
        var responseStepProperty = responseStepItem.get(0).getProperties().getProperty();

        assertThat(responseStepProperty.size(), Matchers.equalTo(1));

        var requestStepPropertiesMatcher = new PropertyItemMatcher()
                .withName(requestStepProperty.get(0).getName())
                .withValue(requestStepProperty.get(0).getValue());

        assertThat(responseStepProperty.get(0), requestStepPropertiesMatcher);
    }

    /**
     * Ensure that its possible to run build via API
     */
    @Test
    @DisplayName("Teamcity API - Check Run build")
    public void testRunBuild() {

        // For some big json payloads I decided to deserialize them using Jackson Object mapper
        // The code looks clearer and understandable
        buildConfigRequest = deserializer.deserialize(
                BUILD_CONFIG_WITHOUT_STEPS_PAYLOAD,
                CreateBuildConfigurationRequest.class);

        buildConfigRequest.setName("test_build" + System.currentTimeMillis());
        buildConfigRequest.setProject(new Project(createdProjectResponse.getId()));

        buildConfigResponse = BuildService.createNewBuildConfiguration(buildConfigRequest);

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

        var addVcsToBuildRequest = AddVCSToBuildRequest.builder()
                .id(vcsRootResponse.getId())
                .vcsRoot(new VcsRoot(vcsRootResponse.getId()))
                .build();

        BuildService.addVCSToBuild(addVcsToBuildRequest, buildConfigResponse.getId());

        var startBuildRequest = StartBuildRequest.builder()
                .buildType(new BuildType(buildConfigResponse.getId()))
                .build();

        var startBuildResponse = BuildService.runBuild(startBuildRequest);

        var startBuildResponseMatcher = new StartBuildResponseMatcher()
                .withBuildTypeId(startBuildRequest.getBuildType().getId())
                .withState("queued");

        assertThat(startBuildResponse, startBuildResponseMatcher);
    }
}
