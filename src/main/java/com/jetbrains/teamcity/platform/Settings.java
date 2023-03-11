package com.jetbrains.teamcity.platform;

import org.testcontainers.containers.BrowserWebDriverContainer;

public class Settings {
    public enum Browser {
        CHROME,
        FIREFOX
    }

    public static final String PROJECT_ROOT_DIR = System.getProperty("project.root.dir", null);

    public static final Browser BROWSER = Browser.valueOf(System.getProperty("framework.browser", Browser.CHROME.name()));

    public static final BrowserWebDriverContainer.VncRecordingMode
            VNC_MODE = BrowserWebDriverContainer.VncRecordingMode.valueOf(
            System.getProperty("framework.vnc.mode",
                    BrowserWebDriverContainer.VncRecordingMode.RECORD_FAILING.name()));
}
