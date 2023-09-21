package com.gcom.operacional.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.gcom.operacional.dto.GenericAddressDto;
import com.gcom.operacional.token.ProfileDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * UserController
 * 
 * @author Jesus Garcia
 */
@Api(value = "UserController")
public interface AddressController {

	/**
	 * Method to list providence
	 * 
	 * @param dto UserAuthRequestDTO.class 
	 * @return
	 */
	@ApiOperation(value = "Get providence List", notes = "Retorna los datos referente a los usuarios de la aplicacion")
	public ResponseEntity<List<GenericAddressDto>> getProvinceOrStateList(Long regionOrCityId);
	
	/**
	 * Method to list RegionOrCity
	 * 
	 * @param dto {@link ProfileDto} 
	 * @return
	 */
	@ApiOperation(value = "Get RegionOrCity list", notes = "Retorna los datos referente a los perfiles de la aplicacion")
	public ResponseEntity<List<GenericAddressDto>> getRegionOrCityList(Long countryId);
	
	/**
	 * Method to list Country
	 * 
	 * @param dto UserAuthRequestDTO.class 
	 * @return
	 */
	@ApiOperation(value = "Save Country", notes = "Inserta o actualiza los usuarios de la aplicacion")
	public ResponseEntity<List<GenericAddressDto>> getCountryList();
	
	
}
