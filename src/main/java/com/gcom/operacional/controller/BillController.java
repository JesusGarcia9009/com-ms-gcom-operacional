package com.gcom.operacional.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.gcom.operacional.dto.BillOfBuyDto;
import com.gcom.operacional.dto.BillOfBuyReverseDto;
import com.gcom.operacional.token.UserPrincipal;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * BillController
 * 
 * @author Jesus Garcia
 */
@Api(value = "BillController")
public interface BillController {
	

	/**
	 * Method to list the Bill of the products
	 * 
	 * @param dto BillDto.class 
	 * @return
	 */
	@ApiOperation(value = "Get Bill list", notes = "Retorna los datos referente a las ordenes de pedido de la aplicacion")
	public ResponseEntity<List<BillOfBuyDto>> findAll()throws Exception;
	
	/**
	 * getBillDocument Method.- Spring Boot
	 *
	 * @param @PathVariable Long id
	 * @param UserPrincipal token
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ResponseEntity<byte[]> see {@link Byte}
	 */
	public ResponseEntity<byte[]> getBillDocument(Long id, UserPrincipal token, HttpServletRequest request, HttpServletResponse response) throws Exception ;
	
	/**
	 * Method to save Bill of the application
	 * 
	 * @param dto BillDto.class 
	 * @return boolean
	 */
	@ApiOperation(value = "Save Bill", notes = "Inserta o actualiza las ordenes de pedido de la aplicacion")
	public ResponseEntity<?> save(BillOfBuyDto request, UserPrincipal token)throws Exception;
	
	/**
	 * Method to save BillOfBuyReverse of the application
	 * 
	 * @param dto BillOfBuyReverseDto.class 
	 * @return boolean
	 */
	@ApiOperation(value = "Save BillOfBuyReverse", notes = "Inserta reversa de factura de compra")
	public ResponseEntity<?> reverse(BillOfBuyReverseDto request, UserPrincipal token)throws Exception;
	
	
	
}
