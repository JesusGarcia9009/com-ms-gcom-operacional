package com.gcom.operacional.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DashboardProductsDataDto {
	
	private Long id;
	private String productDescription;
	private String productGis;
	private Long amount;

}
