package com.gcom.operacional.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.gcom.operacional.dto.ProductDto;
import com.gcom.operacional.dto.ProductSelectDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * ProductController
 * 
 * @author Jesus Garcia
 */
@Api(value = "ProductController")
public interface ProductController {

	/**
	 * Method to list the products of the application
	 * 
	 * @param none
	 * @return dto ProductDto.class 
	 */
	@ApiOperation(value = "Get Product List", notes = "Retorna los datos referente a los prodctos")
	public ResponseEntity<List<ProductDto>> getProductList()throws Exception;
	
	/**
	 * Method to list the products select of the application
	 * 
	 * @param none
	 * @return dto ProductDto.class 
	 */
	@ApiOperation(value = "Get Product List", notes = "Retorna los datos referente a los prodctos para los select")
	public ResponseEntity<List<ProductSelectDto>> getProductSelectList()throws Exception;
	
	/**
	 * Method to find one product in the application
	 * 
	 * @param none
	 * @return dto ProductDto.class 
	 */
	@ApiOperation(value = "Get Product", notes = "Retorna los datos referente a un producto")
	public ResponseEntity<ProductDto> findById(Long productId)throws Exception;
	
	/**
	 * Method to save product of the application
	 * 
	 * @param dto ProductDto.class 
	 * @return
	 */
	@ApiOperation(value = "Save product", notes = "Inserta o actualiza los productos de la aplicacion")
	public ResponseEntity<?> save(ProductDto request)throws Exception;
	
	
	/**
	 * Method to delete product of the application
	 * 
	 * @param dto ProductAuthRequestDTO.class 
	 * @return
	 */
	@ApiOperation(value = "Delete product", notes = "Elimina los usuarios de la aplicacion")
	public ResponseEntity<?> delete(@RequestBody ProductDto request) throws Exception;
	
	
	
}
