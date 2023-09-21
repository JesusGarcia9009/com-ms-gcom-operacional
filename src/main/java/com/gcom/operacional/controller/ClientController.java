package com.gcom.operacional.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.gcom.operacional.dto.ClientDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * ClientController
 * 
 * @author Jesus Garcia
 */
@Api(value = "ClientController")
public interface ClientController {

	/**
	 * Method to list the users of the application
	 * 
	 * @param dto ClientAuthRequestDTO.class 
	 * @return
	 */
	@ApiOperation(value = "Get Client List", notes = "Retorna los datos referente a los usuarios de la aplicacion")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno satisfactorio"),
            @ApiResponse(code = 401, message = "No autorizado"),
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "No encontrado")
    })
	public ResponseEntity<List<ClientDto>> getClientList()throws Exception;
	
	/**
	 * Method to save user of the application
	 * 
	 * @param dto ClientAuthRequestDTO.class 
	 * @return
	 */
	@ApiOperation(value = "Save user", notes = "Inserta o actualiza los usuarios de la aplicacion")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno satisfactorio"),
            @ApiResponse(code = 401, message = "No autorizado"),
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "No encontrado")
    })
	public ResponseEntity<?> save(ClientDto request)throws Exception;
	
	
	/**
	 * Method to delete user of the application
	 * 
	 * @param dto ClientAuthRequestDTO.class 
	 * @return
	 */
	@ApiOperation(value = "Delete user", notes = "Elimina los usuarios de la aplicacion")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno satisfactorio"),
            @ApiResponse(code = 401, message = "No autorizado"),
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "No encontrado")
    })
	public ResponseEntity<?> delete(@RequestBody ClientDto request) throws Exception;
	
	
	
}
