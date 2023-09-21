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
public class OrderNoteDto {
	
	
	private Long id;
	private String updateName;
	private Date generationDate;
	private Date updateDate;
	private Date deliveryDate;
	private BigDecimal discount;
	private BigDecimal iva;
	private boolean showAdditionalInformation;
	private String additionalInformation;
	private String numberOfBill;
	private Date dateOfBill;
	private String numberOfPurchaseOrder;
	private Date dateOfPurchaseOrder;
	private String deliveryType;
	private String transport;
	
	private Long orderNoteStateId;
	private String orderNoteStateName;
	
	private Long clientId;
	private String clientRutOrId;
	private String clientFantasyName;
	
	private List<OrderNoteProductDto> orderNoteProductsList = new ArrayList<>();

	public OrderNoteDto(Long id, String updateName, Date generationDate, Date updateDate, Date deliveryDate,
			BigDecimal discount, BigDecimal iva, boolean showAdditionalInformation, String additionalInformation,
			String numberOfBill, Date dateOfBill, String numberOfPurchaseOrder, Date dateOfPurchaseOrder,
			String deliveryType, String transport, Long orderNoteStateId,
			String orderNoteStateName, Long clientId, String clientRutOrId, String clientFantasyName) {
		super();
		this.id = id;
		this.updateName = updateName;
		this.generationDate = generationDate;
		this.updateDate = updateDate;
		this.deliveryDate = deliveryDate;
		this.discount = discount;
		this.iva = iva;
		this.showAdditionalInformation = showAdditionalInformation;
		this.additionalInformation = additionalInformation;
		this.numberOfBill = numberOfBill;
		this.dateOfBill = dateOfBill;
		this.numberOfPurchaseOrder = numberOfPurchaseOrder;
		this.dateOfPurchaseOrder = dateOfPurchaseOrder;
		this.deliveryType = deliveryType;
		this.transport = transport;
		this.orderNoteStateId = orderNoteStateId;
		this.orderNoteStateName = orderNoteStateName;
		this.clientId = clientId;
		this.clientRutOrId = clientRutOrId;
		this.clientFantasyName = clientFantasyName;
	}

}
