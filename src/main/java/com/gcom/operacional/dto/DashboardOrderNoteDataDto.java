package com.gcom.operacional.dto;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DashboardOrderNoteDataDto {
	
	private String weekDay;
	private BigInteger numberOrderNote;

}
