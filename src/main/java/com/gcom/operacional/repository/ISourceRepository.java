package com.gcom.operacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.dto.ItemDto;
import com.gcom.operacional.entity.Sources;

/**
 * ISourceRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface ISourceRepository extends CrudRepository<Sources, Long> {


	@Query(   "   SELECT new com.gcom.operacional.dto.ItemDto ("
			+ "              b.id, "
			+ "              b.code, "
			+ "              b.description) " 
			+ "     FROM Sources b ")
	List<ItemDto> findAllSources();
	
	Sources findByCode(String code);
	
}
