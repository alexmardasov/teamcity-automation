package com.jetbrains.teamcity.ui.pageobjects;

import com.jetbrains.teamcity.platform.Awaits;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.concurrent.atomic.AtomicReference;

import static com.jetbrains.teamcity.platform.Awaits.*;

public class BuildConfiguration {

    public BuildConfiguration() {
        buildContent();
    }

    private WebElement buildContent() {
        return elem(By.cssSelector("[id='main-content-tag']"));
    }

    public String getSuccessMessage() {
        return elem(By.cssSelector("div#unprocessed_objectsCreated")).getText();
    }
}
