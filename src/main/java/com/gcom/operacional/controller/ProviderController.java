package com.gcom.operacional.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.gcom.operacional.dto.ProviderDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * ProviderController
 * 
 * @author Jesus Garcia
 */
@Api(value = "ProviderController")
public interface ProviderController {

	/**
	 * Method to list the providers of the application
	 * 
	 * @param dto ProviderAuthRequestDTO.class 
	 * @return
	 */
	@ApiOperation(value = "Get Provider List", notes = "Retorna los datos referente a los proveedores de la aplicacion")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno satisfactorio"),
            @ApiResponse(code = 401, message = "No autorizado"),
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "No encontrado")
    })
	public ResponseEntity<List<ProviderDto>> getProviderList()throws Exception;
	
	/**
	 * Method to save provider of the application
	 * 
	 * @param dto ProviderAuthRequestDTO.class 
	 * @return
	 */
	@ApiOperation(value = "Save provider", notes = "Inserta o actualiza los proveedores de la aplicacion")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno satisfactorio"),
            @ApiResponse(code = 401, message = "No autorizado"),
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "No encontrado")
    })
	public ResponseEntity<?> save(ProviderDto request)throws Exception;
	
	
	/**
	 * Method to delete provider of the application
	 * 
	 * @param dto ProviderAuthRequestDTO.class 
	 * @return
	 */
	@ApiOperation(value = "Delete provider", notes = "Elimina los proveedores de la aplicacion")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno satisfactorio"),
            @ApiResponse(code = 401, message = "No autorizado"),
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "No encontrado")
    })
	public ResponseEntity<?> delete(@RequestBody ProviderDto request) throws Exception;
	
	
	
}
