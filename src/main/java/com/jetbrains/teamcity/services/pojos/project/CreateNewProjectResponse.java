package com.jetbrains.teamcity.services.pojos.project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateNewProjectResponse{
	private ParentProject parentProject;
	private String parentProjectId;
	private String webUrl;
	private String name;
	private String id;
	private String href;

	public ParentProject getParentProject(){
		return parentProject;
	}

	public String getParentProjectId(){
		return parentProjectId;
	}

	public String getWebUrl(){
		return webUrl;
	}

	public String getName(){
		return name;
	}

	public String getId(){
		return id;
	}

	public String getHref(){
		return href;
	}
}
