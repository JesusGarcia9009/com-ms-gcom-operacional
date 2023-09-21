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
public class ProductSelectDto {
	
	private Long id;
	private BigDecimal salePrice;
	private BigDecimal netCost;
	private int stock;
	private String rowNumb;
	private String colNumb;
	private String description;
	private String providerDescription;
	
	private Long   modelId;
	private String modelCode;
	private String modelDescription;
	private String modelMeasure;
	private String modelVehicleType;
	private String modelApproximateYear;
	private String modelEngineDescription;
	private String modelTypeOfMotor;
	private String modelNotes;
	private String modelMast;
	
    private Long   brandId;
	private String brandCode;
	private String brandDescription;
	
	private Long   productReferenceId;
	private String productReferenceGis;
	private String productReferenceDesc;
	
    private Long   productTypeId;
	private String productTypeCode;
	private String productTypeDescription;
	
    private Long   sourceId;
	private String sourceCode;
	private String sourceDescription;

    private Long   universalGroupId;
	private String universalGroupCode;
	private String universalGroupDescription;
	
}
