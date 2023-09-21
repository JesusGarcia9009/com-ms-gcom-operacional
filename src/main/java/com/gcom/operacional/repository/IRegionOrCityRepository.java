package com.gcom.operacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.dto.GenericAddressDto;
import com.gcom.operacional.entity.RegionOrCity;

/**
 * IRegionOrCityRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IRegionOrCityRepository extends CrudRepository<RegionOrCity, Long> {


	@Query(   "   SELECT new com.gcom.operacional.dto.GenericAddressDto (c.id ,c.description) " 
			+ "     FROM RegionOrCity c where :countryId is null OR c.country.id = :countryId")
	List<GenericAddressDto> findAllRegionOrCity(Long countryId);
	
	
}
