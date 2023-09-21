package com.gcom.operacional.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BillOfBuyReverseDto {
	
	private Long id;
	private Long idBillOfBuy;
	private String additionalInformation;

}
