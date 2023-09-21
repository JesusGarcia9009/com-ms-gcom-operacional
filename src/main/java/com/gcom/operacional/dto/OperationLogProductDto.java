package com.gcom.operacional.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OperationLogProductDto {
	
	private Long id;
	private long amount;
	private Long productId;
	private String productDescription;
	private String productGISCode;
	private String rowNumb;
	private String colNumb;

}
