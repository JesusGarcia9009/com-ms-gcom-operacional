package com.gcom.operacional.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProviderDto {
	
	private Long id;
	private String rutOrId;
	private String fantasyName;
	private String businessName;
	private String address;
	private String transport;
	private String deliveryObservation;
	private String attachedDocument;

	private Long contactid;
	private String contactName;
	private String contactphone;
	private String contactcellPhone;
	private String contactbusinessMail;
	
	private Long provinceOrStateId;
	private String provinceOrStateDescription;
	
	private Long regionOrCityId;
	private String regionOrCityDescription;
	
	private Long countryId;
	private String countryDescription;
	
	

}
