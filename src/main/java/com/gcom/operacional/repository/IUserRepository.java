package com.gcom.operacional.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.dto.UserDto;
import com.gcom.operacional.entity.Users;

/**
 * IUserRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IUserRepository extends CrudRepository<Users, Long> {

	Optional<Users> findByUsernameOrMail(String Username, String mail);
	
    Users findByRutOrUsername(String rut, String username);
	
	Long countByRutOrUsername(String rut, String username);
	
	@Query(   "   SELECT new com.gcom.operacional.dto.UserDto (u.id ,u.rut ,u.names ,u.middleName ,u.lastName ,u.mail ,u.businessPosition ,u.pass, p.id, p.profileName) " 
			+ "     FROM Users u "
			+ "			 inner join u.profile p ")
	List<UserDto> findAllUsers();
	
	Users findByMailOrRutOrUsername(String mail, String rut, String username);
	
}
