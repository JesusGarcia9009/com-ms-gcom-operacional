package com.gcom.operacional.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.gcom.operacional.dto.InventoryTweaksDto;
import com.gcom.operacional.token.UserPrincipal;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * InventoryTweaksController
 * 
 * @author Jesus Garcia
 */
@Api(value = "InventoryTweaksController")
public interface InventoryTweaksController {

	/**
	 * Method to list the inventoryTweaks of the products
	 * 
	 * @param dto InventoryTweaksDto.class 
	 * @return
	 */
	@ApiOperation(value = "Get InventoryTweaks list", notes = "Retorna los datos de ajustes de inventarios")
	public ResponseEntity<List<InventoryTweaksDto>> getInventoryTweaksList()throws Exception;
	
	/**
	 * Method to save inventoryTweaks of the application
	 * 
	 * @param dto InventoryTweaksDto.class 
	 * @return boolean
	 */
	@ApiOperation(value = "Save InventoryTweaks", notes = "Inserta o actualiza datos de ajustes de inventarios")
	public ResponseEntity<?> save(InventoryTweaksDto request, UserPrincipal token)throws Exception;
	
	/**
	 * Method to change state inventoryTweaks of the application
	 * 
	 * @param dto InventoryTweaksDto.class 
	 * @return boolean
	 */
	@ApiOperation(value = "Implement InventoryTweaks", notes = "Cambia el estado de los ajustes de inventarios")
	public ResponseEntity<?> implement(InventoryTweaksDto request, UserPrincipal token)throws Exception;
	
	
	/**
	 * Method to delete inventoryTweaks of the application
	 * 
	 * @param dto InventoryTweaksDto.class 
	 * @return boolean
	 */
	@ApiOperation(value = "Delete inventoryTweaks", notes = "Elimina los datos de las ajustes de inventarios")
	public ResponseEntity<Boolean> delete(@RequestBody InventoryTweaksDto request,UserPrincipal token) throws Exception;
	
	
	
}
