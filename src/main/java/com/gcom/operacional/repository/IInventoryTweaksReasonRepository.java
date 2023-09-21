package com.gcom.operacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.dto.InventoryTweaksReasonDto;
import com.gcom.operacional.entity.InventoryTweaksReason;

/**
 * IInventoryTweaksReasonRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IInventoryTweaksReasonRepository extends CrudRepository<InventoryTweaksReason, Long> {


	@Query(   "   SELECT new com.gcom.operacional.dto.InventoryTweaksReasonDto ("
			+ "              b.id, "
			+ "              b.description) " 
			+ "     FROM InventoryTweaksReason b ")
	List<InventoryTweaksReasonDto> findAllInventoryTweaksReason();
	
	InventoryTweaksReason findByDescription(String description);
	
}
