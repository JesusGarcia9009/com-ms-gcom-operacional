package com.gcom.operacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.dto.QuotationDeliveryDto;
import com.gcom.operacional.entity.QuotationDelivery;

/**
 * IQuotationDeliveryRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IQuotationDeliveryRepository extends CrudRepository<QuotationDelivery, Long> {


	@Query(   "   SELECT new com.gcom.operacional.dto.QuotationDeliveryDto ("
			+ "              b.id, "
			+ "              b.description) " 
			+ "     FROM QuotationDelivery b ")
	List<QuotationDeliveryDto> findAllQuotationDelivery();
	
	QuotationDelivery findByDescription(String description);
	
}
