package com.gcom.operacional.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryTweaksProductDto {
	
	private Long id;
	private Integer amount;
	private Long productId;
	private String productDescription;
	private String productGISCode;
	private String rowNumb;
	private String colNumb;

}
