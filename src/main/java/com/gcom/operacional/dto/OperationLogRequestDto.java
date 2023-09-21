package com.gcom.operacional.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public @Data class OperationLogRequestDto {
	
	@JsonProperty
	private Date startDate;
	
	@JsonProperty
	private Date endDate;
	
	@JsonProperty
	private String operationType;
	
	@JsonProperty
	private String operationFullName;
	
	@JsonProperty
	private String operationCurrentState;
	
	@JsonProperty
	private Long operationIdObject;

}
