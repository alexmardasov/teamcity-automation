package com.jetbrains.teamcity.services.pojos.vcsroots;

import com.jetbrains.teamcity.services.pojos.common.Project;
import com.jetbrains.teamcity.services.pojos.common.Properties;
import lombok.Builder;
import lombok.Data;
@Builder
public @Data class CreateVCSRootRequest{
	private String name;
	private String vcsName;
	private Project project;
	private String id;
	private Properties properties;

	public String getName(){
		return name;
	}

	public String getVcsName(){
		return vcsName;
	}

	public Project getProject(){
		return project;
	}

	public String getId(){
		return id;
	}

	public Properties getProperties(){
		return properties;
	}
}
