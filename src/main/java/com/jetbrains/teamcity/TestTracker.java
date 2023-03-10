package com.jetbrains.teamcity;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestTracker implements BeforeAllCallback {

    private static final Logger log = LoggerFactory.getLogger(TestTracker.class);
    private TeamcityLauncher teamcityLauncher;
    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        teamcityLauncher = new TeamcityLauncher();
        teamcityLauncher.launch();
        log.info("Teamcity server is started.");
    }
}