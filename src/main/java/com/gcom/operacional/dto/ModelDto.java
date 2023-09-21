package com.gcom.operacional.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ModelDto {
	
	private Long   id;
	private String code;
	private String description;
	private String measure;
	private String vehicleType;
	private String approximateYear;
	private String engineDescription;
	private String typeOfMotor;
	private String notes;
	private String mast;
	private Long   brandId;
	private String brandCode;
	private String brandDescription;

}
