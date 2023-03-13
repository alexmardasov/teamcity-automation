package com.jetbrains.teamcity.ui.pageobjects;

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
