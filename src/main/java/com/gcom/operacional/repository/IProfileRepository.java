package com.gcom.operacional.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.entity.Profile;
import com.gcom.operacional.token.ProfileDto;

/**
 * IProfileRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IProfileRepository extends CrudRepository<Profile, Long> {

	
	Optional<Profile> findByProfileName(String profileName);
	
	@Query(   "   SELECT new com.gcom.operacional.token.ProfileDto (p.id ,p.profileName) " 
			+ "     FROM Users u "
			+ "          INNER JOIN u.profile p "
			+ "    WHERE u.id = :idUser ")
	ProfileDto getProfileByUsername(Long idUser);
	
	@Query(   "   SELECT new com.gcom.operacional.token.ProfileDto (p.id ,p.profileName) " 
			+ "     FROM Profile p ")
	List<ProfileDto> findProfileList();
	
	
	
}
