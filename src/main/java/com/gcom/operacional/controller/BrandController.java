package com.gcom.operacional.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.gcom.operacional.dto.ItemDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * BrandController
 * 
 * @author Jesus Garcia
 */
@Api(value = "BrandController")
public interface BrandController {

	/**
	 * Method to list the brand of the application
	 * 
	 * @param dto ItemDto.class 
	 * @return
	 */
	@ApiOperation(value = "Get brand list", notes = "Retorna los datos referente a las brand de la aplicacion")
	public ResponseEntity<List<ItemDto>> getBrandList()throws Exception;
	
	/**
	 * Method to save brand of the application
	 * 
	 * @param dto ItemDto.class 
	 * @return boolean
	 */
	@ApiOperation(value = "Save brand", notes = "Inserta o actualiza las brand de la aplicacion")
	public ResponseEntity<?> save(ItemDto request)throws Exception;
	
	
	/**
	 * Method to delete brand of the application
	 * 
	 * @param dto ItemDto.class 
	 * @return boolean
	 */
	@ApiOperation(value = "Delete brand", notes = "Elimina los brand de la aplicacion")
	public ResponseEntity<?> delete(@RequestBody ItemDto request) throws Exception;
	
	
	
}
