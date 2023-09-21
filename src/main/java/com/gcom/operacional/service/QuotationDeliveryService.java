package com.gcom.operacional.service;

import java.util.List;

import com.gcom.operacional.dto.QuotationDeliveryDto;


/**
 * QuotationDeliveryService servicio de usuario
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
public interface QuotationDeliveryService {

	
	/**
	 * get source list
	 * 
	 * @param none
	 * @return list @see QuotationDeliveryDto
	 */
	public List<QuotationDeliveryDto> findAllQuotationDelivery();
	
	/**
	 * save source 
	 * 
	 * @param source {@link QuotationDeliveryDto}
	 * @return Boolean
	 */
	public Boolean save(QuotationDeliveryDto source) throws Exception; 	
	
	/**
	 * delete source 
	 * 
	 * @param source {@link QuotationDeliveryDto}
	 * @return Boolean
	 */
	public boolean delete(QuotationDeliveryDto dto) throws Exception;
	
}
