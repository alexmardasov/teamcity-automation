package com.jetbrains.teamcity.services.pojos.build;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class BuildItem{

	@JsonProperty("finishOnAgentDate")
	private String finishOnAgentDate;

	@JsonProperty("number")
	private String number;

	@JsonProperty("buildTypeId")
	private String buildTypeId;

	@JsonProperty("webUrl")
	private String webUrl;

	@JsonProperty("id")
	private int id;

	@JsonProperty("state")
	private String state;

	@JsonProperty("href")
	private String href;

	@JsonProperty("status")
	private String status;

	public String getFinishOnAgentDate(){
		return finishOnAgentDate;
	}

	public String getNumber(){
		return number;
	}

	public String getBuildTypeId(){
		return buildTypeId;
	}

	public String getWebUrl(){
		return webUrl;
	}

	public int getId(){
		return id;
	}

	public String getState(){
		return state;
	}

	public String getHref(){
		return href;
	}

	public String getStatus(){
		return status;
	}
}