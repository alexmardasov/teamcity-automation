package com.jetbrains.teamcity.services.pojos.build;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class AddVCSToBuildRequest{

	@JsonProperty("vcs-root")
	private VcsRoot vcsRoot;

	@JsonProperty("id")
	private String id;

	public VcsRoot getVcsRoot(){
		return vcsRoot;
	}

	public String getId(){
		return id;
	}
}