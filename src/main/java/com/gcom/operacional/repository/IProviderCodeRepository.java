package com.gcom.operacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.entity.ProviderCode;

/**
 * IProviderCodeRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IProviderCodeRepository extends CrudRepository<ProviderCode, Long> {


	ProviderCode findByDescription(String description);
	
	@Query("select g from ProviderCode g where g.product.id = :id ")
	List<ProviderCode> findByProduct(Long id);
	
}
