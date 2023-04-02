package com.jetbrains.teamcity.services.pojos.user;

import java.util.List;

public class Roles{
	private List<RoleItem> role;

	public Roles(List<RoleItem> role) {
		this.role = role;
	}

	public List<RoleItem> getRole(){
		return role;
	}
}