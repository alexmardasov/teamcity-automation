package com.jetbrains.teamcity.ui.pageobjects;

import com.sun.tools.javac.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.jetbrains.teamcity.platform.Awaits.*;


public class MainPage {

    public MainPage() {
        mainPage();
    }
    private WebElement mainPage() {
        return elem(By.cssSelector("[id=bodyWrapper]"));
    }

    public boolean isDisplayed() {
        return mainPage().isDisplayed();
    }

    public MainPage openFromHeader(HeaderItem action) {
        nestedElem(mainPage(), By.cssSelector(action.locator())).click();
        return this;
    }

    public CreateProjectPage createNewProject() {
        nestedElem(mainPage(), By.cssSelector("[class*='button ProjectCreateSection']")).click();
        return new CreateProjectPage();
    }
    public LeftSidebar leftSideBar() {
        return new LeftSidebar();
    }

    public String getBuildStatus(String buildId) {
        return elem(By.cssSelector("[data-build-id='" + buildId + "'] [data-test-link-with-icon*='finished']")).getText();
    }

    public MainPage clickOnBuildStatus(String buildId) {
        elem(By.cssSelector("[data-build-id='" + buildId + "'] [data-test-link-with-icon*='finished']")).click();
        return this;
    }

    public MainPage openArtifacts() {
        elem(By.cssSelector("[data-tab-title='Artifacts']")).click();
        return this;
    }

    public WebElement getArtifactByFileName(String fileName) {
        return elem(By.cssSelector("[data-test='ring-tab'] a[href*='" + fileName + "']"));
    }

    public MainPage runBuild() {
        elem(By.cssSelector("[data-test='run-build']")).click();
        return this;
    }

    public enum HeaderItem {
        PROJECTS("[title='Projects']"),
        CHANGES("[title='Changes']"),
        AGENTS("[title='Agents']"),
        QUEUE("[title='Queue']");

        private final String locator;

        HeaderItem(String locator) {
            this.locator = String.format("a%s[data-test='ring-link']", locator);
        }

        public String locator() {
            return locator;
        }
    }
}
