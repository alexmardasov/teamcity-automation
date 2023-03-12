package com.jetbrains.teamcity.services.pojos.agent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class GetAllAgentResponse{
	private List<AgentItem> agent;
	private int count;
	private String href;

	public List<AgentItem> getAgents() {
		return agent;
	}
}