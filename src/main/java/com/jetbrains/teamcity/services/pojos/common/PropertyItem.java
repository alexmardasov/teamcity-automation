package com.jetbrains.teamcity.services.pojos.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class PropertyItem{

	@JsonProperty("name")
	private String name;

	@JsonProperty("value")
	private String value;

	public String getName(){
		return name;
	}

	public String getValue(){
		return value;
	}
}