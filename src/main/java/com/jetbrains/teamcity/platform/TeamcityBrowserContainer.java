package com.jetbrains.teamcity.platform;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.DefaultRecordingFileFactory;
import org.testcontainers.containers.RecordingFileFactory;

import java.io.File;

public class TeamcityBrowserContainer extends BrowserWebDriverContainer implements RecordingFileFactory {

    private String fileName;
    private File createdFile;

    private TeamcityBrowserContainer(Capabilities capabilities) {
        super();
        this.withRecordingMode(Settings.VNC_MODE, seleniumRecordingDir());
        this.withRecordingFileFactory(this);
        this.withCapabilities(capabilities);
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    @Override
    public File recordingFileForTest(File file, String s, boolean succeed) {
        var fileName = this.fileName;
        if (fileName == null) {
            return new DefaultRecordingFileFactory().recordingFileForTest(file, s, succeed);
        }
        createdFile = new File(file, fileName);
        return createdFile;
    }

    public static TeamcityBrowserContainer provideDriver() {
        switch (Settings.BROWSER) {
            case CHROME:
                return new TeamcityBrowserContainer(chrome());
            case FIREFOX:
                return new TeamcityBrowserContainer(firefox());
            default:
                throw new IllegalArgumentException("Incorrect browser was set.");
        }
    }

    public static ChromeOptions chrome() {
        return new ChromeOptions();
    }

    public static FirefoxOptions firefox() {
        return new FirefoxOptions();
    }

    private File seleniumRecordingDir() {
        var file = new File(Settings.PROJECT_ROOT_DIR + "/logs/vnc-records");
        file.mkdirs();
        if (!file.exists()) {
            throw new IllegalStateException("Selenium recording path " + file + " does not exist and could not be "
                    + "created");
        }
        return file;
    }
}
