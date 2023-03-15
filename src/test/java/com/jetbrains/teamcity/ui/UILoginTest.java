package com.jetbrains.teamcity.ui;

import com.jetbrains.teamcity.platform.TeamcityBrowserContainer;
import com.jetbrains.teamcity.platform.TeamcityContainer;
import com.jetbrains.teamcity.platform.TeamcityWebDriverConfigExtension;
import com.jetbrains.teamcity.ui.pageobjects.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(TeamcityWebDriverConfigExtension.class)
@Testcontainers
public class UILoginTest {
    @Container
    private final TeamcityBrowserContainer<?> browser = TeamcityBrowserContainer.provideDriver();

    @BeforeEach
    public void init(TeamcityContainer teamcity) {
        browser.getWebDriver().get(teamcity.getWebUiUrl());
    }

    /**
     * Ensure that user can log in with Teamcity UI
     */
    @Test
    @DisplayName("Teamcity UI - Check successful login")
    public void testSuccessfulLoginFromUI() {
        var loginPage = new LoginPage();
        var mainPage = loginPage.userName("admin")
                .password("test")
                .login();

        assertTrue(mainPage.createProjectButton().isDisplayed(), "Main Page is not available or user was not logged in.");
    }
}
