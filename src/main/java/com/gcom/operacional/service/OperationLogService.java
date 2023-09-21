package com.gcom.operacional.service;

import java.util.List;

import com.gcom.operacional.dto.OperationLogDto;
import com.gcom.operacional.dto.OperationLogRequestDto;
import com.gcom.operacional.dto.OperationTypeDto;
import com.gcom.operacional.token.UserPrincipal;


/**
 * OperationLogService servicio de usuario
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
public interface OperationLogService {

	
	/**
	 * get operationLog list
	 * 
	 * @param none
	 * @return list @see ItemDto
	 */
	public List<OperationLogDto> findAllOperationLog(OperationLogRequestDto request);
	
	public List<String> findAllUsers();
	
	public List<String> findAllState();
	
	public List<OperationTypeDto> findAllOperationType();
	
	public Boolean saveOperationBillOfBuy(Long idBillOfBuy, String estado, UserPrincipal token) throws Exception;
	
	public Boolean saveOperationOrderNote(Long idOrderNote, String estado , UserPrincipal token) throws Exception;
	
	public Boolean saveOperationInventory(Long idInventoryTweaks, String estado, UserPrincipal token) throws Exception;
	

	
}
