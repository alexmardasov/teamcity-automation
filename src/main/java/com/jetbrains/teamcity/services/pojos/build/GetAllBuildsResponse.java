package com.jetbrains.teamcity.services.pojos.build;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetAllBuildsResponse{

	@JsonProperty("build")
	private List<BuildItem> build;

	public List<BuildItem> getBuild(){
		return build;
	}
}