package com.jetbrains.teamcity.platform;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Timeouts {
    public static final long ELEMENT_WAIT_TIMEOUT = TimeUnit.SECONDS.toSeconds(60);
    public static final long SYSTEM_INIT_TIMEOUT = TimeUnit.SECONDS.toSeconds(30);
    public static final long BUILD_SUCCESS_TIMEOUT = TimeUnit.SECONDS.toSeconds(30);
}
