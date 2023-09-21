package com.gcom.operacional.service;

import java.util.List;

import com.gcom.operacional.dto.ItemDto;


/**
 * ProductTypeService servicio de usuario
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
public interface ProductTypeService {

	
	/**
	 * get type list
	 * 
	 * @param none
	 * @return list @see ItemDto
	 */
	public List<ItemDto> findAllProductType();
	
	/**
	 * save type 
	 * 
	 * @param type {@link ItemDto}
	 * @return Boolean
	 */
	public Boolean save(ItemDto type) throws Exception; 	
	
	/**
	 * delete type 
	 * 
	 * @param type {@link ItemDto}
	 * @return Boolean
	 */
	public boolean delete(ItemDto dto) throws Exception;
	
}
