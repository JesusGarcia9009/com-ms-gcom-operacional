package com.gcom.operacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.dto.DeliveryTypeDto;
import com.gcom.operacional.entity.DeliveryType;

/**
 * IDeliveryTypeRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IDeliveryTypeRepository extends CrudRepository<DeliveryType, Long> {


	@Query(   "   SELECT new com.gcom.operacional.dto.DeliveryTypeDto (dt.id ,dt.code, dt.description ) " 
			+ "     FROM DeliveryType dt ")
	List<DeliveryTypeDto> findAllDeliveryType();
	
	DeliveryType findByCode(String code);
	
}
