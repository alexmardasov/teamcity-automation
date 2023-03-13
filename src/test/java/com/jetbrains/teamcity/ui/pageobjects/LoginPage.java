package com.jetbrains.teamcity.ui.pageobjects;

import lombok.extern.java.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.jetbrains.teamcity.platform.Awaits.*;

public class LoginPage {

    public LoginPage() {
        loginPage();
    }

    private WebElement loginPage() {
        return elem(By.cssSelector("[id=loginPage]"));
    }

    public LoginPage userName(String userName) {
        var input = nestedElem(loginPage(), By.cssSelector("[id=username]"));
        input.sendKeys(userName);
        return this;
    }

    public LoginPage password(String password) {
        var input = nestedElem(loginPage(), By.cssSelector("[id=password]"));
        input.sendKeys(password);
        return this;
    }

    public MainPage login() {
        var button = nestedElem(loginPage(), By.cssSelector("[class='btn loginButton']"));
        button.click();
        return new MainPage();
    }
}
