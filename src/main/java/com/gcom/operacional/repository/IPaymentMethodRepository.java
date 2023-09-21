package com.gcom.operacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.dto.PaymentMethodDto;
import com.gcom.operacional.entity.PaymentMethod;

/**
 * ICountryRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IPaymentMethodRepository extends CrudRepository<PaymentMethod, Long> {


	@Query(   "   SELECT new com.gcom.operacional.dto.PaymentMethodDto(p.id ,p.initials, p.description , p.days ) " 
			+ "     FROM PaymentMethod p ")
	List<PaymentMethodDto> findAllPaymentMethod();
	
	@Query(   "   SELECT new com.gcom.operacional.dto.PaymentMethodDto(p.id ,p.initials, p.description , p.days ) " 
			+ "     FROM PaymentMethod p where p.id = :id ")
	PaymentMethodDto findPaymentMethodById(Long id);
	
	PaymentMethod findByInitials(String initials);
	
}
