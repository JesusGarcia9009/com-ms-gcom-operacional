package com.gcom.operacional.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.gcom.operacional.dto.ItemDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * UniversalGroupsController
 * 
 * @author Jesus Garcia
 */
@Api(value = "UniversalGroupsController")
public interface UniversalGroupsController {

	/**
	 * Method to list the universalGroups of the products
	 * 
	 * @param dto ItemDto.class 
	 * @return
	 */
	@ApiOperation(value = "Get universalGroups list", notes = "Retorna los datos referente a las universalGroups de la aplicacion")
	public ResponseEntity<List<ItemDto>> getUniversalGroupsList()throws Exception;
	
	/**
	 * Method to save universalGroups of the application
	 * 
	 * @param dto ItemDto.class 
	 * @return boolean
	 */
	@ApiOperation(value = "Save universalGroups", notes = "Inserta o actualiza las universalGroups de la aplicacion")
	public ResponseEntity<?> save(ItemDto request)throws Exception;
	
	
	/**
	 * Method to delete universalGroups of the application
	 * 
	 * @param dto ItemDto.class 
	 * @return boolean
	 */
	@ApiOperation(value = "Delete universalGroups", notes = "Elimina los universalGroups de la aplicacion")
	public ResponseEntity<Boolean> delete(@RequestBody ItemDto request) throws Exception;
	
	
	
}
