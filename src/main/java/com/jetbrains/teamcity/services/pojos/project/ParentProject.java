package com.jetbrains.teamcity.services.pojos.project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class ParentProject{
	private String locator;
	public String getLocator(){
		return locator;
	}

	public static ParentProject withLocator(String id) {
		var obj =  new ParentProject();
		obj.setLocator("id:" + id);
		return obj;
	}
}
