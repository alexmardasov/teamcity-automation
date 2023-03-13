package com.jetbrains.teamcity.ui.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.jetbrains.teamcity.platform.Awaits.*;

public class LeftSidebar {

    public LeftSidebar() {
        leftSideBar();
    }

    private WebElement leftSideBar() {
        return elem(By.cssSelector("aside[data-test='sidebar']"));
    }

    public CreateProjectPage createProject() {
        nestedElem(leftSideBar(), By.cssSelector("a[class*='ring-button-button']")).click();
        return new CreateProjectPage();
    }
}
