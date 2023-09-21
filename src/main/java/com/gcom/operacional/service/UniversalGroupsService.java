package com.gcom.operacional.service;

import java.util.List;

import com.gcom.operacional.dto.ItemDto;


/**
 * UniversalGroupsService servicio de usuario
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
public interface UniversalGroupsService {

	
	/**
	 * get universalGroups list
	 * 
	 * @param none
	 * @return list @see ItemDto
	 */
	public List<ItemDto> findAllUniversalGroups();
	
	/**
	 * save universalGroups 
	 * 
	 * @param universalGroups {@link ItemDto}
	 * @return Boolean
	 */
	public Boolean save(ItemDto universalGroups) throws Exception; 	
	
	/**
	 * delete universalGroups 
	 * 
	 * @param universalGroups {@link ItemDto}
	 * @return Boolean
	 */
	public boolean delete(ItemDto dto) throws Exception;
	
}
