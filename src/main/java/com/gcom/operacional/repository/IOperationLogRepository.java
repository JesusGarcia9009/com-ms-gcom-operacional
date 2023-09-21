package com.gcom.operacional.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.dto.OperationLogDto;
import com.gcom.operacional.entity.OperationLog;

/**
 * IOperationLogRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IOperationLogRepository extends CrudRepository<OperationLog, Long> {


	
	@Query(   "   SELECT new com.gcom.operacional.dto.OperationLogDto ("
			+ "          ol.id, "
		    + "          ol.operationUsername, "
			+ "          ol.operationFullName, "
			+ "          ol.operationDate, "
			+ "          ol.operationType, "
			+ "          ol.operationCurrentState, "
			+ "          ol.operationIdObject) " 
			+ "     FROM OperationLog ol "
			+ "    WHERE (ol.operationDate >= :start ) "
			+ "      and (ol.operationDate <= :end )"
			+ "      and (:operationType IS NULL OR ol.operationType = :operationType) "
			+ "      and (:operationFullName IS NULL OR ol.operationFullName = :operationFullName) "
			+ "      and (:operationCurrentState IS NULL OR ol.operationCurrentState = :operationCurrentState)"
			+ "      and (:operationIdObject IS NULL OR ol.operationIdObject = :operationIdObject)")
	List<OperationLogDto> findAllOperationLog(Date start, Date end, String operationType, String operationFullName, String operationCurrentState, Long operationIdObject);
	
	@Query(   "   SELECT ol " 
			+ "     FROM OperationLog ol "
			+ "    WHERE ol.operationIdObject = :operationIdObject and ol.operationType = :operationType ")
	Optional<OperationLog> findOperationLog(Long operationIdObject, String operationType );
	
	@Query(   "   SELECT distinct ol.operationCurrentState " 
			+ "     FROM OperationLog ol ")
	List<String> findAllState();
	
	@Query(   "   SELECT distinct ol.operationFullName " 
			+ "     FROM OperationLog ol ")
	List<String> findAllUsers();
}
