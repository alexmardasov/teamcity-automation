package com.jetbrains.teamcity.services.pojos.agent;

import lombok.Data;

public @Data class AgentItem {
	private String webUrl;
	private String name;
	private int typeId;
	private int id;
	private String href;
}
