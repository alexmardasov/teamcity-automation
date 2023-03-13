package com.jetbrains.teamcity.platform;

import com.google.common.base.Throwables;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.AnnotationSupport;
import org.junit.platform.commons.support.HierarchyTraversalMode;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.junit.jupiter.Container;

import java.lang.reflect.Modifier;

public class JunitUtils {

    static TeamcityBrowserContainer<?> getContainer(ExtensionContext context)
    {
        try {
            var testInstance = context.getRequiredTestInstance();
            var webDriverContainerFields = AnnotationSupport.findAnnotatedFields(
                    testInstance.getClass(),
                    Container.class,
                    f -> f.getType().isAssignableFrom(TeamcityBrowserContainer.class),
                    HierarchyTraversalMode.BOTTOM_UP
            );
            if (webDriverContainerFields != null) {
                for (var field : webDriverContainerFields) {
                    field.setAccessible(true);
                    return (TeamcityBrowserContainer) field.get(testInstance);
                }
            }
        } catch (Exception e) {
            Throwables.throwIfUnchecked(e);
            throw new RuntimeException(e);
        }
        return null;
    }

}
