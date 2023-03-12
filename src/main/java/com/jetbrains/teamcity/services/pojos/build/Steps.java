package com.jetbrains.teamcity.services.pojos.build;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Steps{

	@JsonProperty("step")
	private List<StepItem> step;

	public List<StepItem> getStep(){
		return step;
	}
}