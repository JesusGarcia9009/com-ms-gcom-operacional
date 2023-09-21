package com.gcom.operacional.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.gcom.operacional.dto.InventoryTweaksReasonDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * InventoryTweaksReasonController
 * 
 * @author Jesus Garcia
 */
@Api(value = "InventoryTweaksReasonController")
public interface InventoryTweaksReasonController {

	/**
	 * Method to list the inventoryTweaksReason of the products
	 * 
	 * @param dto InventoryTweaksReasonDto.class 
	 * @return
	 */
	@ApiOperation(value = "Get InventoryTweaksReason list", notes = "Retorna los datos de ajustes de inventarios")
	public ResponseEntity<List<InventoryTweaksReasonDto>> getInventoryTweaksReasonList()throws Exception;
	
	/**
	 * Method to save inventoryTweaksReason of the application
	 * 
	 * @param dto InventoryTweaksReasonDto.class 
	 * @return boolean
	 */
	@ApiOperation(value = "Save InventoryTweaksReason", notes = "Inserta o actualiza datos de ajustes de inventarios")
	public ResponseEntity<?> save(InventoryTweaksReasonDto request)throws Exception;
	
	
	/**
	 * Method to delete inventoryTweaksReason of the application
	 * 
	 * @param dto InventoryTweaksReasonDto.class 
	 * @return boolean
	 */
	@ApiOperation(value = "Delete inventoryTweaksReason", notes = "Elimina los datos de las razones de ajustes de inventarios")
	public ResponseEntity<Boolean> delete(@RequestBody InventoryTweaksReasonDto request) throws Exception;
	
	
	
}
