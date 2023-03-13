package com.jetbrains.teamcity.services.pojos.build;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class StartBuildResponse{

	@JsonProperty("buildTypeId")
	private String buildTypeId;

	@JsonProperty("id")
	private int id;

	@JsonProperty("state")
	private String state;

	public String getBuildTypeId(){
		return buildTypeId;
	}

	public int getId(){
		return id;
	}

	public String getState(){
		return state;
	}
}