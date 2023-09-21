package com.gcom.operacional.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.gcom.operacional.dto.QuotationDeliveryDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * QuotationDeliveryController
 * 
 * @author Jesus Garcia
 */
@Api(value = "QuotationDeliveryController")
public interface QuotationDeliveryController {

	/**
	 * Method to list the quotationDelivery of the products
	 * 
	 * @param dto QuotationDeliveryDto.class 
	 * @return
	 */
	@ApiOperation(value = "Get QuotationDelivery list", notes = "Retorna los datos  de delivery de la cotizacion")
	public ResponseEntity<List<QuotationDeliveryDto>> getQuotationDeliveryList()throws Exception;
	
	/**
	 * Method to save quotationDelivery of the application
	 * 
	 * @param dto QuotationDeliveryDto.class 
	 * @return boolean
	 */
	@ApiOperation(value = "Save QuotationDelivery", notes = "Inserta o actualiza el delivery de la cotizacion")
	public ResponseEntity<?> save(QuotationDeliveryDto request)throws Exception;
	
	
	/**
	 * Method to delete quotationDelivery of the application
	 * 
	 * @param dto QuotationDeliveryDto.class 
	 * @return boolean
	 */
	@ApiOperation(value = "Delete quotationDelivery", notes = "Elimina los datos del delivery de la cotizacion")
	public ResponseEntity<Boolean> delete(@RequestBody QuotationDeliveryDto request) throws Exception;
	
	
	
}
