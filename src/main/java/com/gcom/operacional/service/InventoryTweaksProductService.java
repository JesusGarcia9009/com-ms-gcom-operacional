package com.gcom.operacional.service;

import com.gcom.operacional.dto.InventoryTweaksDto;
import com.gcom.operacional.entity.InventoryTweaks;
import com.gcom.operacional.token.UserPrincipal;


/**
 * InventoryTweaksProductService servicio de productos de inventario
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
public interface InventoryTweaksProductService {
	
	
	/**
	 * save Inventory Tweaks 
	 * 
	 * @param dto {@link InventoryTweaksDto}
	 * @param model {@link InventoryTweaks}
	 * @return Boolean
	 */
	public Boolean save(InventoryTweaksDto dto, InventoryTweaks model, UserPrincipal token) throws Exception; 	
	
	
	
	
	
}
