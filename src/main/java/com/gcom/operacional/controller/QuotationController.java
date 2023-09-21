package com.gcom.operacional.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.gcom.operacional.dto.QuotationDto;
import com.gcom.operacional.token.UserPrincipal;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * QuotationController
 * 
 * @author Jesus Garcia
 */
@Api(value = "QuotationController")
public interface QuotationController {

	/**
	 * Method to list the quotation of the products
	 * 
	 * @param dto QuotationDto.class 
	 * @return
	 */
	@ApiOperation(value = "Get quotation list", notes = "Retorna los datos referente a las cotizaciones de la aplicacion")
	public ResponseEntity<List<QuotationDto>> findAll()throws Exception;
	
	/**
	 * Method to find one quotation by code
	 * 
	 * @param id @See Long
	 * @return dto QuotationDto.class 
	 */
	@ApiOperation(value = "Get quotation ", notes = "Retorna los datos referente a una cotizacion")
	public ResponseEntity<QuotationDto> findById(Long id)throws Exception;
	
	/**
	 * getQuotationDocument Method.- Spring Boot
	 *
	 * @param @PathVariable Long id
	 * @param UserPrincipal token
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ResponseEntity<byte[]> see {@link Byte}
	 */
	public ResponseEntity<byte[]> getQuotationDocument(Long id, UserPrincipal token, HttpServletRequest request, HttpServletResponse response) throws Exception ;
	
	/**
	 * Method to save quotation of the application
	 * 
	 * @param dto QuotationDto.class 
	 * @return boolean
	 */
	@ApiOperation(value = "Save quotation", notes = "Inserta o actualiza las cotizaciones de la aplicacion")
	public ResponseEntity<?> save(QuotationDto request, UserPrincipal token)throws Exception;
	
	
	/**
	 * Method to delete quotation of the application
	 * 
	 * @param dto QuotationDto.class 
	 * @return boolean
	 */
	@ApiOperation(value = "Delete quotation", notes = "Elimina las cotizaciones de la aplicacion")
	public ResponseEntity<Boolean> delete(@RequestBody QuotationDto request) throws Exception;
	
	
	
}
