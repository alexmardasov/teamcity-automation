package com.jetbrains.teamcity.services.pojos.user;

public class RoleItem{
	private String roleId;
	private String scope;

	public RoleItem(String roleId, String scope) {
		this.roleId = roleId;
		this.scope = scope;
	}

	public String getRoleId(){
		return roleId;
	}

	public String getScope(){
		return scope;
	}

}
