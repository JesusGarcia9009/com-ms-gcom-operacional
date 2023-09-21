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
public class QuotationProductDto {
	
	private Long id;
	private BigDecimal amount;
	private BigDecimal salePrice;
	private String delivery;
	private Long productId;
	private String productDescription;
	private String productGISCode;
	private String rowNumb;
	private String colNumb;
	
	public QuotationProductDto(Long id, BigDecimal amount, BigDecimal salePrice, String delivery, Long productId,
			String productDescription, String productGISCode) {
		super();
		this.id = id;
		this.amount = amount;
		this.salePrice = salePrice;
		this.delivery = delivery;
		this.productId = productId;
		this.productDescription = productDescription;
		this.productGISCode = productGISCode;
	}
	
	

}
