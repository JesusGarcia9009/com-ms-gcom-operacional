package com.gcom.operacional.service;

import java.util.List;

import com.gcom.operacional.dto.InventoryTweaksDto;
import com.gcom.operacional.token.UserPrincipal;


/**
 * InventoryTweaksService servicio de ajustes de inventario
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
public interface InventoryTweaksService {

	
	/**
	 * get inventoryTweaks list
	 * 
	 * @param none
	 * @return list @see InventoryTweaksDto
	 */
	public List<InventoryTweaksDto> findAllInventoryTweaks();
	
	/**
	 * save inventoryTweaks 
	 * 
	 * @param inventoryTweaks {@link InventoryTweaksDto}
	 * @return Boolean
	 */
	public Boolean save(InventoryTweaksDto dto, UserPrincipal token) throws Exception; 	
	
	/**
	 * save inventoryTweaks 
	 * 
	 * @param inventoryTweaks {@link InventoryTweaksDto}
	 * @return Boolean
	 */
	public Boolean implement(InventoryTweaksDto dto, UserPrincipal token) throws Exception;
	
	/**
	 * delete inventoryTweaks 
	 * 
	 * @param inventoryTweaks {@link InventoryTweaksDto}
	 * @return Boolean
	 */
	public boolean delete(InventoryTweaksDto dto, UserPrincipal token) throws Exception;
	
}
