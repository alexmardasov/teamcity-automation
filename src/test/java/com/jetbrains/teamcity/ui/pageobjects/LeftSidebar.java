package com.jetbrains.teamcity.ui.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

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

    public LeftSidebar searchProject(String projectName) {
        elem(By.cssSelector("input#search-projects")).sendKeys(projectName);
        return this;
    }

    public LeftSidebar selectProjectAndExpand(String projectName) {
        var projectElems = elems(By.cssSelector("[data-test-itemtype='project']"));

        var foundProject = projectElems.stream()
                .filter(elem -> elem.getText().equals(projectName)
                        && elem.findElement(By.cssSelector("button[data-test='expand-button']")) != null)
                .collect(Collectors.toList()).get(0);

        nestedElem(foundProject, By.cssSelector("button[data-test='expand-button']")).click();
        return this;
    }

    public LeftSidebar selectBuild(String buildTypeId) {
        elem(By.cssSelector("[data-buildtype-id='" + buildTypeId + "']")).click();
        return this;
    }
}
