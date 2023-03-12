package com.jetbrains.teamcity.services.pojos.build;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class StartBuildRequest{

	@JsonProperty("buildType")
	private BuildType buildType;

	public BuildType getBuildType(){
		return buildType;
	}
}