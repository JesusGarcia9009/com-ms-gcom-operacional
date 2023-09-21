package com.gcom.operacional.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.entity.OrderNoteState;

/**
 * IOrderNoteStateRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IOrderNoteStateRepository extends CrudRepository<OrderNoteState, Long> {
	
	Optional<OrderNoteState> findByNameState(String nameState);


}
