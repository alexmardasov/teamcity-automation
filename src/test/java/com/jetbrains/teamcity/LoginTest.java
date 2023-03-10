package com.jetbrains.teamcity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
public class LoginTest {

    @Container
    private final TeamcityBrowserContainer browser = TeamcityBrowserContainer.provideDriver();


    @BeforeAll
    public static void init() {

    }

    @Test
    public void loginTest() {
        var driver = browser.getWebDriver();
        driver.get("http://host.docker.internal:8112");
    }
}
