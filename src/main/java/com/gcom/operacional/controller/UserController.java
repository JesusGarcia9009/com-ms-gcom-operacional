package com.gcom.operacional.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.gcom.operacional.dto.UserDto;
import com.gcom.operacional.token.ProfileDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * UserController
 * 
 * @author Jesus Garcia
 */
@Api(value = "UserController")
public interface UserController {

	/**
	 * Method to list the users of the application
	 * 
	 * @param dto UserAuthRequestDTO.class 
	 * @return
	 */
	@ApiOperation(value = "Get User List", notes = "Retorna los datos referente a los usuarios de la aplicacion")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno satisfactorio"),
            @ApiResponse(code = 401, message = "No autorizado"),
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "No encontrado")
    })
	public ResponseEntity<List<UserDto>> getUserList()throws Exception;
	
	/**
	 * Method to list the profiles of the application
	 * 
	 * @param dto {@link ProfileDto} 
	 * @return
	 */
	@ApiOperation(value = "Get profile list", notes = "Retorna los datos referente a los perfiles de la aplicacion")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno satisfactorio"),
            @ApiResponse(code = 401, message = "No autorizado"),
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "No encontrado")
    })
	public ResponseEntity<List<ProfileDto>> getProfileList()throws Exception;
	
	/**
	 * Method to save user of the application
	 * 
	 * @param dto UserAuthRequestDTO.class 
	 * @return
	 */
	@ApiOperation(value = "Save user", notes = "Inserta o actualiza los usuarios de la aplicacion")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno satisfactorio"),
            @ApiResponse(code = 401, message = "No autorizado"),
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "No encontrado")
    })
	public ResponseEntity<?> save(UserDto request)throws Exception;
	
	
	/**
	 * Method to delete user of the application
	 * 
	 * @param dto UserAuthRequestDTO.class 
	 * @return
	 */
	@ApiOperation(value = "Delete user", notes = "Elimina los usuarios de la aplicacion")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno satisfactorio"),
            @ApiResponse(code = 401, message = "No autorizado"),
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "No encontrado")
    })
	public ResponseEntity<?> delete(@RequestBody UserDto request) throws Exception;
	
	
	
}
