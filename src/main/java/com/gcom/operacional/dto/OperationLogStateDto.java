package com.gcom.operacional.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OperationLogStateDto {
	
	private String operationUsername;
	private String operationFullName;
	private Date operationDate;
	private String operationState;

}
