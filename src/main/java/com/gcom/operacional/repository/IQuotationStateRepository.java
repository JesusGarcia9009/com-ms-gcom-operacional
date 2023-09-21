package com.gcom.operacional.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.entity.QuotationState;

/**
 * IQuotationStateRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IQuotationStateRepository extends CrudRepository<QuotationState, Long> {

	Optional<QuotationState> findByNameState(String nameState);
}
