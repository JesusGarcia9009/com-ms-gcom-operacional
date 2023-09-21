package com.gcom.operacional.dto;

import java.math.BigDecimal;
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
public class QuotationDto {
	
	private Long id;
	private String updateName;
	private String updateMail;
	private Date updateDate;
	private Date generationDate;
	private BigDecimal discount;
	private BigDecimal iva;
	private String attention;
	private String additionalInformation;
	
	private Long clientId;
	private String clientRutOrId;
	private String clientFantasyName;
	
	private Long quotationStateId;
	private String quotationStateName;
	
	private BigDecimal total;
	
	private List<QuotationProductDto> quotationProductList;

	public QuotationDto(Long id, String updateName, String updateMail, Date updateDate, Date generationDate, BigDecimal discount, BigDecimal iva, String attention, String additionalInformation, Long clientId,
			String clientRutOrId, String clientFantasyName, Long quotationStateId, String quotationStateName) {
		super();
		this.id = id;
		this.updateName = updateName;
		this.updateMail = updateMail;
		this.updateDate = updateDate;
		this.generationDate = generationDate;
		this.discount = discount;
		this.iva = iva;
		this.attention = attention;
		this.additionalInformation = additionalInformation;
		this.clientId = clientId;
		this.clientRutOrId = clientRutOrId;
		this.clientFantasyName = clientFantasyName;
		this.quotationStateId = quotationStateId;
		this.quotationStateName = quotationStateName;
	}

}
