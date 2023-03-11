package com.jetbrains.teamcity.services.pojos.user;

public class CreateTokenResponse{
	private String creationTime;
	private String name;
	private String value;

	public String getCreationTime(){
		return creationTime;
	}

	public String getName(){
		return name;
	}

	public String getValue(){
		return value;
	}
}
