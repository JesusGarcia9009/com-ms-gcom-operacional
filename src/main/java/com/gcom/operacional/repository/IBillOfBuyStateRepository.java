package com.gcom.operacional.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.entity.BillOfBuyState;

/**
 * IBillOfBuyStateRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IBillOfBuyStateRepository extends CrudRepository<BillOfBuyState, Long> {
	
	Optional<BillOfBuyState> findByNameState(String nameState);


}
