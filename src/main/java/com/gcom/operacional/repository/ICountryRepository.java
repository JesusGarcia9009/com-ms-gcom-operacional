package com.gcom.operacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.dto.GenericAddressDto;
import com.gcom.operacional.entity.Country;

/**
 * ICountryRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface ICountryRepository extends CrudRepository<Country, Long> {


	@Query(   "   SELECT new com.gcom.operacional.dto.GenericAddressDto (c.id ,c.description) " 
			+ "     FROM Country c ")
	List<GenericAddressDto> findAllCountry();
	
	
}
