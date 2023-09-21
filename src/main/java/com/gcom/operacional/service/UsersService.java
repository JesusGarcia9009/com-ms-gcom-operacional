package com.gcom.operacional.service;

import java.util.List;

import com.gcom.operacional.dto.UserDto;
import com.gcom.operacional.entity.Users;


/**
 * UsersService servicio de usuario
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
public interface UsersService {

	
	/**
	 * get user by field username
	 * 
	 * @param string username
	 * @return model @see User
	 */
	public Users getUserByUsername(String username); 
	
	/**
	 * count users with username
	 * 
	 * @param string userNameOrRut
	 * @return Long - count number of users
	 */
	public Long countUsersByUsername(String username) ;
	
	/**
	 * get user list
	 * 
	 * @param string username
	 * @return list @see UserResponseDTO
	 */
	public List<UserDto> findAllUsers();
	
	/**
	 * save user 
	 * 
	 * @param user {@link UserDto}
	 * @return Boolean
	 */
	public Boolean save(UserDto user) throws Exception; 	
	
	/**
	 * delete user 
	 * 
	 * @param user {@link UserDto}
	 * @return Boolean
	 */
	public boolean delete(UserDto dto) throws Exception;
	
}
