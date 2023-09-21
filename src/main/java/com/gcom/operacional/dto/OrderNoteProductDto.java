package com.gcom.operacional.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderNoteProductDto {
	
	private Long id;
	private BigDecimal amount;
	private BigDecimal salePrice;
	private Long productId;
	private String productDescription;
	private String productGISCode;
	private String rowNumb;
	private String colNumb;
	
	public OrderNoteProductDto(Long id, BigDecimal amount, BigDecimal salePrice, Long productId,
			String productDescription, String productGISCode) {
		super();
		this.id = id;
		this.amount = amount;
		this.salePrice = salePrice;
		this.productId = productId;
		this.productDescription = productDescription;
		this.productGISCode = productGISCode;
	}
	
	

}
