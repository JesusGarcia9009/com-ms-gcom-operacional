package com.gcom.operacional.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserAuthRequestDTO {

	@JsonProperty
	@NotBlank(message = "Es obligatorio")
	private String username;
	
	@JsonProperty
	@NotBlank(message = "Es obligatorio")
	private String password;
}
