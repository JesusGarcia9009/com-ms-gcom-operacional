package com.gcom.operacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.dto.ItemDto;
import com.gcom.operacional.entity.ProductType;

/**
 * IProductTypeRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IProductTypeRepository extends CrudRepository<ProductType, Long> {


	ProductType findByCode(String code);
	
	@Query(   "   SELECT new com.gcom.operacional.dto.ItemDto ("
			+ "              pt.id, "
			+ "              pt.code, "
			+ "              pt.description) " 
			+ "     FROM ProductType pt ")
	List<ItemDto> findAllProductTypes();
	
}
