package com.gcom.operacional.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserAuthResponseDTO {
	
	@JsonProperty
	private Long id;
	
	@JsonProperty
	private String username;
	
	@JsonProperty
	private String fullName;
	
	@JsonProperty
	private String profile;
	
	@JsonProperty
	private String token;

}
