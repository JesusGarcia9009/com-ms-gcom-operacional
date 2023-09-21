package com.gcom.operacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.dto.OperationLogStateDto;
import com.gcom.operacional.entity.OperationLogState;

/**
 * IOperationLogStateRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IOperationLogStateRepository extends CrudRepository<OperationLogState, Long> {


	@Query(   "   SELECT new com.gcom.operacional.dto.OperationLogStateDto ( "
			+ "          ols.operationUsername, "
			+ "          ols.operationFullName, "
			+ "          ols.operationDate, "
			+ "          ols.operationState) "
			+ "     FROM OperationLog ol "
			+ "          inner join ol.operationLogStates ols "
			+ "    WHERE ol.id = :idOperationLog")
	List<OperationLogStateDto> findOperationLogStateByIdOperationLog(Long idOperationLog);
	
	
}
