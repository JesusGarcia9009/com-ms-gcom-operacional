package com.gcom.operacional.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.gcom.operacional.dto.ItemDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * ProductTypeController
 * 
 * @author Jesus Garcia
 */
@Api(value = "ProductTypeController")
public interface ProductTypeController {

	/**
	 * Method to list the type of the products
	 * 
	 * @param dto ItemDto.class 
	 * @return
	 */
	@ApiOperation(value = "Get type list", notes = "Retorna los datos referente a las type de la aplicacion")
	public ResponseEntity<List<ItemDto>> getProductTypeList()throws Exception;
	
	/**
	 * Method to save type of the application
	 * 
	 * @param dto ItemDto.class 
	 * @return boolean
	 */
	@ApiOperation(value = "Save type", notes = "Inserta o actualiza las type de la aplicacion")
	public ResponseEntity<?> save(ItemDto request)throws Exception;
	
	
	/**
	 * Method to delete type of the application
	 * 
	 * @param dto ItemDto.class 
	 * @return boolean
	 */
	@ApiOperation(value = "Delete type", notes = "Elimina los type de la aplicacion")
	public ResponseEntity<Boolean> delete(@RequestBody ItemDto request) throws Exception;
	
	
	
}
