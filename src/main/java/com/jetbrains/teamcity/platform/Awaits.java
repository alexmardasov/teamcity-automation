package com.jetbrains.teamcity.platform;

import com.google.common.base.Throwables;
import com.jetbrains.teamcity.services.BuildService;
import org.junit.rules.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Callable;

public class Awaits {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Locates the element by the given locator. Wait for it to become visible within specified timeout
     *
     * @param locator locator for the element
     * @return element
     */
    public static WebElement elem(By locator) {
        var wait = new WebDriverWait(driver.get(), Timeouts.ELEMENT_WAIT_TIMEOUT);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Locates the nested element by the given locator. Wait for it to become visible within specified timeout
     * @param element parent element
     * @param locator locator for the element
     * @return element
     */
    public static WebElement nestedElem(WebElement element, By locator) {
        var wait = new WebDriverWait(driver.get(), Timeouts.ELEMENT_WAIT_TIMEOUT);
        return wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, locator));
    }

    /**
     * Locates all elements by the given locator, waiting for them to become visible
     * @param locator locator for an element
     * @return elements
     */
    public static List<WebElement> elems(By locator) {
        return new WebDriverWait(getDriver(), Timeouts.ELEMENT_WAIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    /**
     * Makes thread sleep for {@link Timeouts#POLL_INTERVAL} time.
     */
    public static void doSleep() {
        try {
            Thread.sleep(Timeouts.POLL_INTERVAL);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }


    public static void setDriver(WebDriver driver) {
        Awaits.driver.set(driver);
    }

    public static WebDriver getDriver() {
        return Awaits.driver.get();
    }
}
