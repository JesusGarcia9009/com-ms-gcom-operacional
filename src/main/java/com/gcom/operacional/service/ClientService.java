package com.gcom.operacional.service;

import java.util.List;

import com.gcom.operacional.dto.ClientDto;


/**
 * ClientService servicio de usuario
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
public interface ClientService {

	
	/**
	 * get user list
	 * 
	 * @param string username
	 * @return list @see UserResponseDTO
	 */
	public List<ClientDto> findAllClient();
	
	/**
	 * get client by id
	 * 
	 * @param id
	 * @return dto @see UserResponseDTO
	 */
	public ClientDto findClientById(Long id);
	
	/**
	 * save user 
	 * 
	 * @param user {@link ClientDto}
	 * @return Boolean
	 */
	public Boolean save(ClientDto user) throws Exception; 	
	
	/**
	 * delete user 
	 * 
	 * @param user {@link ClientDto}
	 * @return Boolean
	 */
	public boolean delete(ClientDto dto) throws Exception;
	
}
