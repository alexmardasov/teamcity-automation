package com.jetbrains.teamcity.services.pojos.build;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateBuildConfigurationResponse{

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private String id;

	@JsonProperty("projectId")
	private String projectId;

	@JsonProperty("parameters")
	private Parameters parameters;

	@JsonProperty("steps")
	private Steps steps;

	public String getName(){
		return name;
	}

	public String getId(){
		return id;
	}

	public String getProjectId(){
		return projectId;
	}

	public Parameters getParameters(){
		return parameters;
	}

	public Steps getSteps(){
		return steps;
	}
}