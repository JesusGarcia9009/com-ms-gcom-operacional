package com.gcom.operacional.service;

import java.util.List;

import com.gcom.operacional.dto.ModelDto;


/**
 * ModelService servicio de usuario
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
public interface ModelService {

	
	/**
	 * get model list
	 * 
	 * @param Long idBrand @see {@link Long}
	 * @return list @see ModelDto
	 */
	public List<ModelDto> getListByBrand(Long idBrand);
	
	/**
	 * save model 
	 * 
	 * @param model {@link ModelDto}
	 * @return Boolean
	 */
	public Boolean save(ModelDto model) throws Exception; 	
	
	/**
	 * delete model 
	 * 
	 * @param model {@link ModelDto}
	 * @return Boolean
	 */
	public boolean delete(ModelDto dto) throws Exception;
	
}
