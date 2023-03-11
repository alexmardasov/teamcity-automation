package com.jetbrains.teamcity.services.pojos.user;

public class CreateNewUserRequest {
	String name;
	private String password;
	private String email;
	private String username;

	public String getPassword(){
		return password;
	}

	public String getEmail(){
		return email;
	}

	public String getUsername(){
		return username;
	}

	public String getName() {
		return name;
	}

	public static CreateNewUserRequest createUserWithBasicParameters() {
		var userRequest = new CreateNewUserRequest();
		userRequest.name = "test_user" + System.currentTimeMillis();
		userRequest.email = System.currentTimeMillis() + "@test.com";
		userRequest.password = "1234566esdq>";
		userRequest.username = "name" + System.currentTimeMillis();
		return userRequest;
	}
}
