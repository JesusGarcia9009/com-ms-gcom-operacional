package com.gcom.operacional.service;

import java.util.List;

import com.gcom.operacional.dto.ProductDto;
import com.gcom.operacional.dto.ProductSelectDto;


/**
 * ProductService servicio de usuario
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
public interface ProductService {

	
	/**
	 * get Product list
	 * 
	 * @param none
	 * @return list @see UserResponseDTO
	 */
	public List<ProductDto> findAllProduct();
	
	/**
	 * get Product list for select
	 * 
	 * @param none
	 * @return list @see UserResponseDTO
	 */
	public List<ProductSelectDto> findAllProductSelect();
	
	/**
	 * get Product by ID
	 * 
	 * @param idProduct @See {@link ProductDto}
	 * @return dto @see UserResponseDTO
	 */
	public ProductDto findById(Long idProduct);
	
	/**
	 * save Product 
	 * 
	 * @param user {@link ProductDto}
	 * @return Boolean
	 */
	public Boolean save(ProductDto user) throws Exception; 	
	
	/**
	 * delete Product 
	 * 
	 * @param user {@link ProductDto}
	 * @return Boolean
	 */
	public boolean delete(ProductDto dto) throws Exception;
	
}
