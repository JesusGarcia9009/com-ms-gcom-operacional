package com.gcom.operacional.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.gcom.operacional.dto.PaymentMethodDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * PaymentMethodController
 * 
 * @author Jesus Garcia
 */
@Api(value = "PaymentMethodController")
public interface PaymentMethodController {

	/**
	 * Method to list delivery type
	 * 
	 * @param dto UserAuthRequestDTO.class 
	 * @return response @see {@link PaymentMethodDto}
	 */
	@ApiOperation(value = "get all PaymentMethodDto", notes = "rescata la informacion referente a los metodos de pago")
	public ResponseEntity<List<PaymentMethodDto>> getPaymentMethodList();
	
	/**
	 * Method to save PaymentMethodDto of the application
	 * 
	 * @param dto PaymentMethodDto.class 
	 * @return boolean
	 */
	@ApiOperation(value = "Save paymentMethod", notes = "Inserta o actualiza las paymentMethod de la aplicacion")
	public ResponseEntity<?> save(PaymentMethodDto request)throws Exception;
	
	
	/**
	 * Method to delete paymentMethod of the application
	 * 
	 * @param dto PaymentMethodDto.class 
	 * @return boolean
	 */
	@ApiOperation(value = "Delete paymentMethod", notes = "Elimina los paymentMethod de la aplicacion")
	public ResponseEntity<?> delete(PaymentMethodDto request) throws Exception;
	
	
}
