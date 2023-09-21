package com.gcom.operacional.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OperationLogDto {
	
	private Long id;
	private String operationUsername;
	private String operationFullName;
	private Date operationDate;
	private String operationType;
	private String operationCurrentState;
	private Long operationIdObject;
	
	private List<OperationLogStateDto> operationLogStates = new ArrayList<OperationLogStateDto>(0);
	private List<OperationLogProductDto> operationLogProducts = new ArrayList<OperationLogProductDto>(0);
	
	
	public OperationLogDto(Long id, String operationUsername, String operationFullName, Date operationDate,
			String operationType, String operationCurrentState, Long operationIdObject) {
		super();
		this.id = id;
		this.operationUsername = operationUsername;
		this.operationFullName = operationFullName;
		this.operationDate = operationDate;
		this.operationType = operationType;
		this.operationCurrentState = operationCurrentState;
		this.operationIdObject = operationIdObject;
	}
	
	
	
	
}
