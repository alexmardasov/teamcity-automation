package com.jetbrains.teamcity.services.pojos.build;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data class BuildType{

	@JsonProperty("id")
	private String id;

}