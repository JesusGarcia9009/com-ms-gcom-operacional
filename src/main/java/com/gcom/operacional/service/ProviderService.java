package com.gcom.operacional.service;

import java.util.List;

import com.gcom.operacional.dto.ProviderDto;


/**
 * ProviderService servicio de usuario
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
public interface ProviderService {

	
	/**
	 * get provider list
	 * 
	 * @param 
	 * @return list @see ProviderDto
	 */
	public List<ProviderDto> findAllProvider();
	
	/**
	 * save provider 
	 * 
	 * @param user {@link ProviderDto}
	 * @return Boolean
	 */
	public Boolean save(ProviderDto user) throws Exception; 	
	
	/**
	 * delete provider 
	 * 
	 * @param provider {@link ProviderDto}
	 * @return Boolean
	 */
	public boolean delete(ProviderDto dto) throws Exception;
	
}
