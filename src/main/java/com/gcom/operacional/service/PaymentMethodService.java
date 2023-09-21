package com.gcom.operacional.service;

import java.util.List;

import com.gcom.operacional.dto.PaymentMethodDto;


/**
 * PaymentMethodService servicio de metodos de pago
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
public interface PaymentMethodService {

	
	/**
	 * get all Country
	 * 
	 * @param none
	 * @return list @see PaymentMethodDto
	 */
	public List<PaymentMethodDto> getPaymentMethodList();
	
	/**
	 * get PaymentMethod bay id
	 * 
	 * @param none
	 * @return dto @see PaymentMethodDto
	 */
	public PaymentMethodDto findPaymentMethodById(Long id);
	
	/**
	 * save PaymentMethod 
	 * 
	 * @param dto {@link PaymentMethodDto}
	 * @return Boolean
	 */
	public Boolean save(PaymentMethodDto dto) throws Exception; 	
	
	/**
	 * delete PaymentMethod 
	 * 
	 * @param dto {@link PaymentMethodDto}
	 * @return Boolean
	 */
	public boolean delete(PaymentMethodDto dto) throws Exception;
	
}
