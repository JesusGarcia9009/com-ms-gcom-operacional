package com.gcom.operacional.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DashboardDataDto {
	
	private BigInteger productsGreater;
	private BigInteger productsLess;
	
	private BigInteger billOfBuyNumber;
	
	private BigInteger quotationNumber;
	private BigDecimal quotationAmount;
	
	private BigInteger orderNoteNumber;
	private BigDecimal orderNoteAmount;
	
	List<DashboardQuotationDataDto> quotationList = new ArrayList<>();
	List<DashboardOrderNoteDataDto> orderNoteList = new ArrayList<>();
	List<DashboardProductsDataDto> moreSellersProduct = new ArrayList<>();
	List<DashboardProductsDataDto> lessSellersProduct = new ArrayList<>();

}
