package com.jetbrains.teamcity.services.pojos.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserItem{
	private int id;
	private String href;
	private String username;

	public int getId(){
		return id;
	}

	public String getHref(){
		return href;
	}

	public String getUsername(){
		return username;
	}
}
