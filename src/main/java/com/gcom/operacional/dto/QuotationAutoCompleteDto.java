package com.gcom.operacional.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuotationAutoCompleteDto {
	
	@JsonProperty
	private Long id;
	
	@JsonProperty
	private Long clientId;
	
	@JsonProperty
	private String clientRutOrId;
	
	@JsonProperty
	private String clientFantasyName;

}
