package com.gcom.operacional.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.gcom.operacional.dto.ItemDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * SourceController
 * 
 * @author Jesus Garcia
 */
@Api(value = "SourceController")
public interface SourceController {

	/**
	 * Method to list the source of the products
	 * 
	 * @param dto ItemDto.class 
	 * @return
	 */
	@ApiOperation(value = "Get source list", notes = "Retorna los datos referente a las source de la aplicacion")
	public ResponseEntity<List<ItemDto>> getSourceList()throws Exception;
	
	/**
	 * Method to save source of the application
	 * 
	 * @param dto ItemDto.class 
	 * @return boolean
	 */
	@ApiOperation(value = "Save source", notes = "Inserta o actualiza las source de la aplicacion")
	public ResponseEntity<?> save(ItemDto request)throws Exception;
	
	
	/**
	 * Method to delete source of the application
	 * 
	 * @param dto ItemDto.class 
	 * @return boolean
	 */
	@ApiOperation(value = "Delete source", notes = "Elimina los source de la aplicacion")
	public ResponseEntity<Boolean> delete(@RequestBody ItemDto request) throws Exception;
	
	
	
}
