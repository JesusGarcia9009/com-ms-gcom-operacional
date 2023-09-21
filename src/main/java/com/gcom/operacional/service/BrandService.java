package com.gcom.operacional.service;

import java.util.List;

import com.gcom.operacional.dto.ItemDto;


/**
 * BrandService servicio de usuario
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
public interface BrandService {

	
	/**
	 * get brand list
	 * 
	 * @param none
	 * @return list @see ItemDto
	 */
	public List<ItemDto> findAllBrand();
	
	/**
	 * save brand 
	 * 
	 * @param brand {@link ItemDto}
	 * @return Boolean
	 */
	public Boolean save(ItemDto brand) throws Exception; 	
	
	/**
	 * delete brand 
	 * 
	 * @param brand {@link ItemDto}
	 * @return Boolean
	 */
	public boolean delete(ItemDto dto) throws Exception;
	
}
