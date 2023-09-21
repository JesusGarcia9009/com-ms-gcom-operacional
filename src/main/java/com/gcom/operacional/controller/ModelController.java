package com.gcom.operacional.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.gcom.operacional.dto.ModelDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * ModelController
 * 
 * @author Jesus Garcia
 */
@Api(value = "ModelController")
public interface ModelController {

	/**
	 * Method to list the model of the application
	 * 
	 * @param Long idBrand
	 * @return dto @See {@link ModelDto}
	 */
	@ApiOperation(value = "Get model list by idModel", notes = "Retorna los datos referente a las model de la aplicacion")
	public ResponseEntity<List<ModelDto>> getListByBrand(Long idBrand)throws Exception;
	
	/**
	 * Method to save model of the application
	 * 
	 * @param dto ItemDto.class 
	 * @return boolean
	 */
	@ApiOperation(value = "Save model", notes = "Inserta o actualiza las model de la aplicacion")
	public ResponseEntity<?> save(ModelDto request)throws Exception;
	
	
	/**
	 * Method to delete model of the application
	 * 
	 * @param dto ItemDto.class 
	 * @return boolean
	 */
	@ApiOperation(value = "Delete model", notes = "Elimina los model de la aplicacion")
	public ResponseEntity<?> delete(ModelDto request) throws Exception;
	
	
	
}
