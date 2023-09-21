package com.gcom.operacional.service;

import java.util.List;

import com.gcom.operacional.dto.InventoryTweaksReasonDto;


/**
 * InventoryTweaksReasonService servicio de usuario
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
public interface InventoryTweaksReasonService {

	
	/**
	 * get inventoryTweaksReason list
	 * 
	 * @param none
	 * @return list @see InventoryTweaksReasonDto
	 */
	public List<InventoryTweaksReasonDto> findAllInventoryTweaksReason();
	
	/**
	 * save inventoryTweaksReason 
	 * 
	 * @param inventoryTweaksReason {@link InventoryTweaksReasonDto}
	 * @return Boolean
	 */
	public Boolean save(InventoryTweaksReasonDto dto) throws Exception; 	
	
	/**
	 * delete inventoryTweaksReason 
	 * 
	 * @param inventoryTweaksReason {@link InventoryTweaksReasonDto}
	 * @return Boolean
	 */
	public boolean delete(InventoryTweaksReasonDto dto) throws Exception;
	
}
