package com.gcom.operacional.dto;

import java.math.BigDecimal;
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
public class BillOfBuyDto {
	
	private Long id;
	private String username;
	private Date generationDate;
	private Date updateDate;
	private BigDecimal discount;
	private BigDecimal iva;
	private String additionalInformation;
	
	private Long billOfBuyStateId;
	private String billOfBuyStateName;
	
	private Long providerId;
	private String providerRutOrId;
	private String providerFantasyName;

	private List<BillOfBuyProductDto> billOfBuyProductsList = new ArrayList<>();

	public BillOfBuyDto(Long id, String username, Date generationDate, Date updateDate, BigDecimal discount,
			BigDecimal iva, String additionalInformation, Long billOfBuyStateId,
			String billOfBuyStateName, Long providerId, String providerRutOrId, String providerFantasyName) {
		super();
		this.id = id;
		this.username = username;
		this.generationDate = generationDate;
		this.updateDate = updateDate;
		this.discount = discount;
		this.iva = iva;
		this.additionalInformation = additionalInformation;
		this.billOfBuyStateId = billOfBuyStateId;
		this.billOfBuyStateName = billOfBuyStateName;
		this.providerId = providerId;
		this.providerRutOrId = providerRutOrId;
		this.providerFantasyName = providerFantasyName;
	}

	
}
