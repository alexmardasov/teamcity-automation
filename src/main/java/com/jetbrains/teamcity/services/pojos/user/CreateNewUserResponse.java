package com.jetbrains.teamcity.services.pojos.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateNewUserResponse{
	private String name;
	private int id;
	private String href;
	private String email;
	private String username;

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public String getHref(){
		return href;
	}

	public String getEmail(){
		return email;
	}

	public String getUsername(){
		return username;
	}
}
