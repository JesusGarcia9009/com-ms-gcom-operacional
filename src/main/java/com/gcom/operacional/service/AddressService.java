package com.gcom.operacional.service;

import java.util.List;

import com.gcom.operacional.dto.GenericAddressDto;


/**
 * UsersService servicio de usuario
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
public interface AddressService {

	
	/**
	 * get all ProvinceOrState
	 * 
	 * @param Long regionOrCityId
	 * @return model @see GenericAddressDto
	 */
	public List<GenericAddressDto> getProvinceOrStateList(Long regionOrCityId);
	
	/**
	 * get all RegionOrCity
	 * 
	 * @param Long regionOrCityId
	 * @return model @see GenericAddressDto
	 */
	public List<GenericAddressDto> getRegionOrCityList(Long countryId);
	
	/**
	 * get all Country
	 * 
	 * @param Long regionOrCityId
	 * @return model @see GenericAddressDto
	 */
	public List<GenericAddressDto> getCountryList();
	
	
	
}
