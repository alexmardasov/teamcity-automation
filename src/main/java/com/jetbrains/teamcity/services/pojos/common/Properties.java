package com.jetbrains.teamcity.services.pojos.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jetbrains.teamcity.services.pojos.common.PropertyItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public  class Properties{
	@JsonProperty("property")
	private List<PropertyItem> property;

	public List<PropertyItem> getProperty(){
		return property;
	}
}