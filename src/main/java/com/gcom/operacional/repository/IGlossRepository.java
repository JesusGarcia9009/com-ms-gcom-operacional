package com.gcom.operacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.entity.Gloss;
import com.gcom.operacional.entity.Product;

/**
 * ICountryRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IGlossRepository extends CrudRepository<Gloss, Long> {


	Gloss findByDescriptionAndProduct(String description, Product product);
	
	@Query("select g from Gloss g where g.product.id = :id ")
	List<Gloss> findByProduct(Long id);
	
}
