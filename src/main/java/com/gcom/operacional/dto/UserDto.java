package com.gcom.operacional.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	@JsonProperty
	private Long id;
	
	@NotNull
	@NotBlank
	@JsonProperty
	private String rut;
	
	@JsonProperty
	private String names;
	
	@JsonProperty
	private String middleName;
	
	@JsonProperty
	private String lastName;
	
	@NotNull
	@NotBlank
	@JsonProperty
	private String mail;
	
	@JsonProperty
	private String businessPosition;
	
	@NotNull
	@NotBlank
	@JsonProperty
	private String pass;
	
	@NotNull
	@JsonProperty
	private Long profileId;
	
	@JsonProperty
	private String profileName;

}
