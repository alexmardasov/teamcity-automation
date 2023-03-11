package com.jetbrains.teamcity.services;

import io.github.marmer.annotationprocessing.MatcherConfiguration;

/**
 * This bootstraps automatic matcher generation. See https://github.com/marmer/hamcrest-matcher-generator
 */
@MatcherConfiguration({
        "com.jetbrains.teamcity.services.pojos.user",
        "com.jetbrains.teamcity.services.pojos.project"
})
public class MatcherGeneratorConfiguration {
}
