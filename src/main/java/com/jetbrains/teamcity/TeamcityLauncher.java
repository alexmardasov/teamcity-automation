package com.jetbrains.teamcity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.io.File;
import java.time.Duration;

public class TeamcityLauncher {

    private static final Logger log = LoggerFactory.getLogger(TeamcityLauncher.class);
    private static final String SERVER_NAME = "teamcity";

    public TeamcityLauncher() {
    }

    public void launch() {
        log.info("Starting container with Teamcity...");
        var container = new DockerComposeContainer<>(getComposeFile())
                .withExposedService(
                        SERVER_NAME, 8111,
                        Wait.defaultWaitStrategy().withStartupTimeout(Duration.ofSeconds(10)))
                .withEnv("HOST_IP_ADDRESS", SERVER_NAME)
                .withLocalCompose(true);

        container.start();
    }

    private static File getComposeFile() {
        return new File("./docker/teamcity-compose.yml");
    }
}
