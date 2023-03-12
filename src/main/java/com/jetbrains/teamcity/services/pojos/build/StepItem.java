package com.jetbrains.teamcity.services.pojos.build;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jetbrains.teamcity.services.pojos.common.Properties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StepItem{

	@JsonProperty("name")
	private String name;

	@JsonProperty("type")
	private String type;

	@JsonProperty("properties")
	private Properties properties;

	public String getName(){
		return name;
	}

	public String getType(){
		return type;
	}

	public Properties getProperties(){
		return properties;
	}
}