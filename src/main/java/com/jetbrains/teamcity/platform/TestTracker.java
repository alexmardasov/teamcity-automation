package com.jetbrains.teamcity.platform;

import com.jetbrains.teamcity.services.AgentService;
import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestTracker implements BeforeAllCallback, ParameterResolver {
    private static final Logger log = LoggerFactory.getLogger(TestTracker.class);
    private TeamcityContainer teamcityLauncher;
    private static boolean systemStarted = false;
    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        if (!systemStarted) {
            teamcityLauncher = new TeamcityContainer();
            teamcityLauncher.launch();
            log.info("Teamcity server is started.");
            // When system has already started we need to authorize all the agents
            AgentService.authorizeAgents();
            systemStarted = true;
        }
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(TeamcityContainer.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return teamcityLauncher;
    }
}