package com.jetbrains.teamcity.services.pojos.build;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuildType{

	@JsonProperty("id")
	private String id;

}