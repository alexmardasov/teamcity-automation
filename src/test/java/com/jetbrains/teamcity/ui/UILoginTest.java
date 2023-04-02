package com.jetbrains.teamcity.ui;

import com.jetbrains.teamcity.platform.BrowserTest;
import com.jetbrains.teamcity.platform.TeamcityBrowserContainer;
import com.jetbrains.teamcity.platform.TeamcityContainer;
import com.jetbrains.teamcity.services.UserService;
import com.jetbrains.teamcity.services.pojos.user.CreateUserRequest;
import com.jetbrains.teamcity.ui.pageobjects.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;

import static org.junit.jupiter.api.Assertions.assertTrue;

@BrowserTest
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

        var createNewUserRequest = CreateUserRequest.createAdminUserWithBasicParameters();
        UserService.createNewUser(createNewUserRequest);

        var loginPage = new LoginPage();
        var mainPage = loginPage.userName(createNewUserRequest.getUsername())
                .password(createNewUserRequest.getPassword())
                .login();

        assertTrue(mainPage.createProjectButton().isDisplayed(), "Main Page is not available or user was not logged in.");
    }
}
