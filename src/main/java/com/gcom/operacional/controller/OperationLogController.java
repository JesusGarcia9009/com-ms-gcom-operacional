package com.gcom.operacional.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.gcom.operacional.dto.OperationLogDto;
import com.gcom.operacional.dto.OperationLogRequestDto;
import com.gcom.operacional.dto.OperationTypeDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * OperationLogController
 * 
 * @author Jesus Garcia
 */
@Api(value = "OperationLogController")
public interface OperationLogController {
	


	@ApiOperation(value = "Get plate info for test ", notes = "Returns the data referent to Plate for share screen")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful return")})
	public ResponseEntity<List<OperationLogDto>> getRecordByDates(OperationLogRequestDto request) ;
	
	@ApiOperation(value = "get Users", notes = "Obtiene los usuarios que han insertado operaciones en el sistema")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful return")})
	public ResponseEntity<List<String>> getUserList();
	
	@ApiOperation(value = "Get States", notes = "Obtiene los estados de las operaciones del sistema")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful return")})
	public ResponseEntity<List<String>> getStateList();
	
	@ApiOperation(value = "Get Types", notes = "Obtiene los tipos de operacion de las operaciones del sistema")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful return")})
	public ResponseEntity<List<OperationTypeDto>> getOperationTypeList();
	
}
