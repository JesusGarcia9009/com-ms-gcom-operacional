package com.gcom.operacional.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	
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
	
	////tablas externas
	private List<String> originalCodeList;
	private List<String> providerCodeList;
	private List<String> glossList;
	
	
	public ProductDto(Long id, BigDecimal salePrice, BigDecimal netCost,int stock,  String rowNumb, String colNumb,
			String description, String providerDescription, Long modelId, String modelCode, String modelDescription,
			String modelMeasure, String modelVehicleType, String modelApproximateYear, String modelEngineDescription,
			String modelTypeOfMotor, String modelNotes, String modelMast, Long brandId, String brandCode,
			String brandDescription, Long productReferenceId, String productReferenceGis, String productReferenceDesc, 
			Long productTypeId, String productTypeCode, String productTypeDescription, Long sourceId, 
			String sourceCode, String sourceDescription, Long universalGroupId, String universalGroupCode, String universalGroupDescription) {
		super();
		this.id = id;
		this.salePrice = salePrice;
		this.netCost = netCost;
		this.stock = stock;
		this.rowNumb = rowNumb;
		this.colNumb = colNumb;
		this.description = description;
		this.providerDescription = providerDescription;
		this.modelId = modelId;
		this.modelCode = modelCode;
		this.modelDescription = modelDescription;
		this.modelMeasure = modelMeasure;
		this.modelVehicleType = modelVehicleType;
		this.modelApproximateYear = modelApproximateYear;
		this.modelEngineDescription = modelEngineDescription;
		this.modelTypeOfMotor = modelTypeOfMotor;
		this.modelNotes = modelNotes;
		this.modelMast = modelMast;
		this.brandId = brandId;
		this.brandCode = brandCode;
		this.brandDescription = brandDescription;
		this.productReferenceId = productReferenceId;
		this.productReferenceGis = productReferenceGis;
		this.productReferenceDesc = productReferenceDesc;
		this.productTypeId = productTypeId;
		this.productTypeCode = productTypeCode;
		this.productTypeDescription = productTypeDescription;
		this.sourceId = sourceId;
		this.sourceCode = sourceCode;
		this.sourceDescription = sourceDescription;
		this.universalGroupId = universalGroupId;
		this.universalGroupCode = universalGroupCode;
		this.universalGroupDescription = universalGroupDescription;
	}
	
	
	
	

}
