package com.jetbrains.teamcity.services.pojos.build;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class VcsRoot{

	@JsonProperty("id")
	private String id;

	public String getId(){
		return id;
	}
}