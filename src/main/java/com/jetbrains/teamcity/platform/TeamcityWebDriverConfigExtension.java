package com.jetbrains.teamcity.platform;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TeamcityWebDriverConfigExtension implements BeforeEachCallback, AfterTestExecutionCallback {

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        var container = JunitUtils.getContainer(context);
        assert container != null;
        container.start();
        Awaits.setDriver(container.getWebDriver());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        var result = context.getExecutionException().isEmpty() ? "PASSED" : "FAILED";
        var displayName = context.getDisplayName();
        var container = JunitUtils.getContainer(context);
        assert container != null;
        var fileName = correctFileName(result, displayName);
        container.setFileName(fileName);
    }

    private String correctFileName(String result, String testName) {
        var dateTime = LocalDateTime.now()
                .format(DateTimeFormatter.ISO_DATE_TIME)
                .replaceAll("\\.[^.]+$", "");
        return String.format("%s %s %s.flv", result, dateTime, testName);

    }
}
