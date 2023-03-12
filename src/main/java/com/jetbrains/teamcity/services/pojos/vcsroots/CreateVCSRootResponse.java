package com.jetbrains.teamcity.services.pojos.vcsroots;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jetbrains.teamcity.services.pojos.common.Properties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateVCSRootResponse{
	private String name;
	private String vcsName;
	private String id;
	private String href;
	private Properties properties;

	public String getName(){
		return name;
	}

	public String getVcsName(){
		return vcsName;
	}

	public String getId(){
		return id;
	}

	public String getHref(){
		return href;
	}

	public Properties getProperties(){
		return properties;
	}
}
