package com.jetbrains.teamcity.ui.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static com.jetbrains.teamcity.platform.Awaits.*;
public class CreateProjectPage {

    public CreateProjectPage() {
        createProjectFirstScreen();
    }

    public CreateProjectPage createFrom(Options option) {
        nestedElem(createProjectFirstScreen(), By.cssSelector(option.locator())).click();
        return this;
    }

    public CreateProjectPage setUrl(String url) {
        nestedElem(createFromUrlForm(), By.cssSelector("input[id=url]")).sendKeys(url);
        return this;
    }

    public CreateProjectPage setUserName(String userName) {
        nestedElem(createFromUrlForm(), By.cssSelector("input[id=username]")).sendKeys(userName);
        return this;
    }

    public CreateProjectPage setPasswordOrToken(String value) {
        nestedElem(createFromUrlForm(), By.cssSelector("input[id=password]")).sendKeys(value);
        return this;
    }

    public CreateProjectPage setProjectName(String projectName) {
        var input = nestedElem(createProjectFirstScreen(), By.cssSelector("[id='projectName']"));
        input.clear();
        input.sendKeys(projectName);
        return this;
    }

    public CreateProjectPage setBuildConfigurationName(String buildConfigurationName) {
        var input = nestedElem(createProjectFirstScreen(), By.cssSelector("[id='buildTypeName']"));
        input.clear();
        input.sendKeys(buildConfigurationName);
        return this;
    }

    public CreateProjectPage setDefaultBranch(String defaultBranch) {
        nestedElem(createProjectFirstScreen(), By.cssSelector("[id='branch']")).sendKeys(defaultBranch);
        return this;
    }

    public CreateProjectPage proceedFirstScreen() {
        nestedElem(createFromUrlForm(),
                By.cssSelector("input[type='submit']")).click();
        return this;
    }
    public CreateProjectPage proceedSecondScreen() {
        nestedElem(createProjectSecondScreen(),
                By.cssSelector("input[type='submit']")).click();
        return this;
    }

    public String getConnectionMessage() {
        return nestedElem(createProjectSecondScreen(), By.cssSelector("[class='connectionSuccessful']")).getText();
    }

    private WebElement createProjectFirstScreen() {
        return elem(By.cssSelector("[id='content'] > [id='main-content-tag']"));
    }

    private WebElement createFromUrlForm() {
        return nestedElem(createProjectFirstScreen(), By.cssSelector("[id='createFromUrlForm']"));
    }

    private WebElement createProjectSecondScreen() {
        return elem(By.cssSelector("[id='createProjectForm']"));
    }

    public enum Options {
        FROM_URL("a[href='#createFromUrl']"),
        MANUALLY("a[href='#createManually']");

        private final String locator;

        Options(String locator) {
            this.locator = locator;
        }

        public String locator() {
            return locator;
        }
    }
}
