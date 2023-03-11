package com.jetbrains.teamcity.services.pojos.user;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
public @Data class CreateTokenRequest{
	private LocalDateTime creationTime;
	private LocalDateTime expirationTime;
	private String name;
	private String value;

}
