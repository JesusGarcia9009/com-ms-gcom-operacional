package com.gcom.operacional.service;

import java.util.List;

import com.gcom.operacional.dto.DeliveryTypeDto;


/**
 * DeliveryTypeService servicio de metodos de envio
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
public interface DeliveryTypeService {

	
	/**
	 * get all DeliveryType
	 * 
	 * @param none
	 * @return list @see DeliveryTypeDto
	 */
	public List<DeliveryTypeDto> getDeliveryTypeList();
	
	/**
	 * save DeliveryType 
	 * 
	 * @param dto {@link DeliveryTypeDto}
	 * @return Boolean
	 */
	public Boolean save(DeliveryTypeDto dto) throws Exception; 	
	
	/**
	 * delete DeliveryType 
	 * 
	 * @param brand {@link DeliveryTypeDto}
	 * @return Boolean
	 */
	public boolean delete(DeliveryTypeDto dto) throws Exception;
	
	
	
}
