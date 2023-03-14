package com.jetbrains.teamcity.ui.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import static com.jetbrains.teamcity.platform.Awaits.*;

public class BuildConfiguration {

    public String getSuccessMessage() {
        return elem(By.cssSelector("div#unprocessed_objectsCreated")).getText();
    }
}
