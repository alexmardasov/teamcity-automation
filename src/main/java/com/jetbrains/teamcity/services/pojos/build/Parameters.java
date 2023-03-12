package com.jetbrains.teamcity.services.pojos.build;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jetbrains.teamcity.services.pojos.common.PropertyItem;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Parameters{

	@JsonProperty("property")
	private List<PropertyItem> property;

	public List<PropertyItem> getProperty(){
		return property;
	}
}