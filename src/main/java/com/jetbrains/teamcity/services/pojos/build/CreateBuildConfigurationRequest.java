package com.jetbrains.teamcity.services.pojos.build;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jetbrains.teamcity.services.pojos.common.Project;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class CreateBuildConfigurationRequest{

	@JsonProperty("templates")
	private Templates templates;

	@JsonProperty("name")
	private String name;

	@JsonProperty("project")
	private Project project;

	@JsonProperty("parameters")
	private Parameters parameters;

	@JsonProperty("steps")
	private Steps steps;
}