package com.gcom.operacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.dto.ItemDto;
import com.gcom.operacional.entity.Brand;

/**
 * IBrandRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IBrandRepository extends CrudRepository<Brand, Long> {


	@Query(   "   SELECT new com.gcom.operacional.dto.ItemDto ("
			+ "              b.id, "
			+ "              b.code, "
			+ "              b.description) " 
			+ "     FROM Brand b ")
	List<ItemDto> findAllBrands();
	
	Brand findByCode(String code);
	
}
