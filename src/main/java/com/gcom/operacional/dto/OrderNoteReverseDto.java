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
public class OrderNoteReverseDto {
	
	
	private Long id;
	private Long idOrderNote;
	private Long numberOfCreditNote;
	private Date dateOfCreditNote;
	private String additionalInformation;
	
	

}
