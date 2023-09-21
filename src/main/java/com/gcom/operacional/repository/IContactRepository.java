package com.gcom.operacional.repository;

import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.entity.Contact;

/**
 * IContactRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IContactRepository extends CrudRepository<Contact, Long> {


	
	
}
