package com.jetbrains.teamcity.platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.io.File;
import java.time.Duration;

public class TeamcityContainer {

    private static final Logger log = LoggerFactory.getLogger(TeamcityContainer.class);
    private static final String TC_SERVICE_NAME = "teamcity";

    public TeamcityContainer() {
    }

    /**
     * Method that is launched the container with Teamcity from docker-compose file.
     * If the checked message isn't appear at the log within 3 minutes, it fails with the error.
     */
    public void launch() {
        log.info("Starting container with Teamcity...");
        var container = new DockerComposeContainer<>(getComposeFile())
                .withExposedService(TC_SERVICE_NAME, 8111)
                .withEnv("HOST_IP_ADDRESS", TC_SERVICE_NAME)
                .waitingFor(TC_SERVICE_NAME, Wait.forLogMessage(".*TeamCity is running.*\\n", 1)
                .withStartupTimeout(Duration.ofMinutes(4)))
                .withPull(true);

        container.start();
    }

    public String getWebUiUrl() {
        return "http://host.docker.internal:8112";
    }

    private static File getComposeFile() {
        return new File("./docker/teamcity-compose.yml");
    }
}
