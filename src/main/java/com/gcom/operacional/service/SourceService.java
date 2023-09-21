package com.gcom.operacional.service;

import java.util.List;

import com.gcom.operacional.dto.ItemDto;


/**
 * SourceService servicio de usuario
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
public interface SourceService {

	
	/**
	 * get source list
	 * 
	 * @param none
	 * @return list @see ItemDto
	 */
	public List<ItemDto> findAllSource();
	
	/**
	 * save source 
	 * 
	 * @param source {@link ItemDto}
	 * @return Boolean
	 */
	public Boolean save(ItemDto source) throws Exception; 	
	
	/**
	 * delete source 
	 * 
	 * @param source {@link ItemDto}
	 * @return Boolean
	 */
	public boolean delete(ItemDto dto) throws Exception;
	
}
