package com.gcom.operacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.dto.OperationLogProductDto;
import com.gcom.operacional.entity.OperationLogProduct;

/**
 * IOperationLogProductRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IOperationLogProductRepository extends CrudRepository<OperationLogProduct, Long> {


	@Query(   "   SELECT new com.gcom.operacional.dto.OperationLogProductDto ( "
			+ "              olp.id,"
			+ "	             olp.amount,"
			+ "	             p.id,"
			+ "	             p.description,"
			+ "              m.code || '-' || g.code || '-' || b.code || '-' || s.code,"
			+ "			     p.rowNumb,"
			+ "              p.colNumb ) " 
			+ "     FROM OperationLog ol "
			+ "          inner join ol.operationLogProducts olp "
			+ "			 inner join olp.product p "
			+ "			 inner join p.model m "
			+ "			 inner join m.brand b "
			+ "			 inner join p.universalGroups g "
			+ "			 inner join p.sources s "
			+ "    WHERE ol.id = :idOperationLog")
	List<OperationLogProductDto> findOperationLogProductByIdOperationLog(Long idOperationLog);
	
	@Query(   "   SELECT olp " 
			+ "     FROM OperationLogProduct olp "
			+ "          inner join olp.operationLog ol "
			+ "    WHERE ol.id = :idOperationLog")
	List<OperationLogProduct> findOperationLogProductByIdOperation(Long idOperationLog);
	
}
