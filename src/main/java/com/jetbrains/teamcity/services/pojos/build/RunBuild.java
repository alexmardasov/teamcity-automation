package com.jetbrains.teamcity.services.pojos.build;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RunBuild{

	@JsonProperty("buildType")
	private BuildType buildType;

	public BuildType getBuildType(){
		return buildType;
	}
}