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
public class OrderNoteBIllDto {
	
	
	private Long id;
	private String numberOfBill;
	private Date dateOfBill;
	private String numberOfPurchaseOrder;
	private Date dateOfPurchaseOrder;
	
	

}
