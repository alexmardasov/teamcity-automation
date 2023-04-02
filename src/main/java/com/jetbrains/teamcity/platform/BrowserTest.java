package com.jetbrains.teamcity.platform;

import org.junit.jupiter.api.extension.ExtendWith;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.lang.annotation.*;

/**
 * This is meta-annotation to mark test classes that contain browser tests via Testcontainers.
 * This also adds some additional configuration for webdriver.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Testcontainers
@ExtendWith(TeamcityWebDriverConfigExtension.class)
public @interface BrowserTest {
}
