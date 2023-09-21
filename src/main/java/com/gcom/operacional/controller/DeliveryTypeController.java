package com.gcom.operacional.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.gcom.operacional.dto.DeliveryTypeDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * DeliveryTypeController
 * 
 * @author Jesus Garcia
 */
@Api(value = "DeliveryTypeController")
public interface DeliveryTypeController {

	/**
	 * Method to list delivery type
	 * 
	 * @param none
	 * @return response @see {@link DeliveryTypeDto}
	 */
	@ApiOperation(value = "get all DeliveryTypeDto", notes = "rescata la informacion referente a los metodos de delivery")
	public ResponseEntity<List<DeliveryTypeDto>> getDeliveryTypeList();
	
	/**
	 * Method to save deliveryType of the application
	 * 
	 * @param dto DeliveryTypeDto.class 
	 * @return boolean
	 */
	@ApiOperation(value = "Save deliveryType", notes = "Inserta o actualiza las deliveryType de la aplicacion")
	public ResponseEntity<?> save(DeliveryTypeDto request)throws Exception;
	
	
	/**
	 * Method to delete deliveryType of the application
	 * 
	 * @param dto DeliveryTypeDto.class 
	 * @return boolean
	 */
	@ApiOperation(value = "Delete deliveryType", notes = "Elimina los deliveryType de la aplicacion")
	public ResponseEntity<?> delete(DeliveryTypeDto request) throws Exception;
	
	
}
