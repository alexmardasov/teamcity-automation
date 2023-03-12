package com.jetbrains.teamcity.services;

import io.github.marmer.annotationprocessing.MatcherConfiguration;

/**
 * This bootstraps automatic matcher generation. See https://github.com/marmer/hamcrest-matcher-generator
 */
@MatcherConfiguration({
        "com.jetbrains.teamcity.services.pojos.common",
        "com.jetbrains.teamcity.services.pojos.user",
        "com.jetbrains.teamcity.services.pojos.project",
        "com.jetbrains.teamcity.services.pojos.build",
        "com.jetbrains.teamcity.services.pojos.agent",
        "com.jetbrains.teamcity.services.pojos.vcsroots"
})
public class MatcherGeneratorConfiguration {
}
