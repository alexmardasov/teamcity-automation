package com.jetbrains.teamcity.platform;

import com.jetbrains.teamcity.services.AgentService;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestTracker implements BeforeAllCallback {
    private static final Logger log = LoggerFactory.getLogger(TestTracker.class);
    private TeamcityLauncher teamcityLauncher;
    private static boolean systemStarted = false;
    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        if (!systemStarted) {
            teamcityLauncher = new TeamcityLauncher();
            teamcityLauncher.launch();
            log.info("Teamcity server is started.");
            // When system has already started we need to authorize all the agents
            AgentService.authorizeAgents();
            systemStarted = true;
        }
    }
}