package com.gcom.operacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.entity.OriginalCode;

/**
 * IOriginalCodeRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IOriginalCodeRepository extends CrudRepository<OriginalCode, Long> {


	OriginalCode findByDescription(String description);
	
	@Query("select g from OriginalCode g where g.product.id = :id ")
	List<OriginalCode> findByProduct(Long id);
	
}
