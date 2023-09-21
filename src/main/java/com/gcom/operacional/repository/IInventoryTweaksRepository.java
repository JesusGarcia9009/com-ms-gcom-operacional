package com.gcom.operacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.dto.InventoryTweaksDto;
import com.gcom.operacional.entity.InventoryTweaks;

/**
 * IInventoryTweaksReasonRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IInventoryTweaksRepository extends CrudRepository<InventoryTweaks, Long> {


	@Query(   "   SELECT new com.gcom.operacional.dto.InventoryTweaksDto ( it.id, it.username, it.dateInventoryTweaks, it.reason, it.inventoryTweaksState, it.additionalInformation) " 
			+ "     FROM InventoryTweaks it ")
	List<InventoryTweaksDto> findAllInventoryTweaks();
	
}
