package com.jetbrains.teamcity.services.pojos.user;

import java.util.List;

public class CreateUserRequest {
	private String name;
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
	private Roles roles;

	public Roles getRoles(){
		return roles;
	}

	public static CreateUserRequest createAdminUserWithBasicParameters() {
		var userRequest = new CreateUserRequest();
		userRequest.name = "test_user" + System.currentTimeMillis();
		userRequest.email = System.currentTimeMillis() + "@test.com";
		userRequest.password = "1234566esdq>";
		userRequest.username = "name" + System.currentTimeMillis();
		userRequest.roles = new Roles(List.of(
				new RoleItem("SYSTEM_ADMIN", "g")
		));
		return userRequest;
	}
}
