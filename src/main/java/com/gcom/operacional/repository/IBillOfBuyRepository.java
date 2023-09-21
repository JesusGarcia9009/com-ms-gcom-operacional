package com.gcom.operacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.dto.BillOfBuyDto;
import com.gcom.operacional.entity.BillOfBuy;

/**
 * IBillOfBuyRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IBillOfBuyRepository extends CrudRepository<BillOfBuy, Long> {


	@Query(   "   SELECT new com.gcom.operacional.dto.BillOfBuyDto ( "
			+ "              b.id, "
			+ "	             b.username, "
			+ "	             b.generationDate, "
			+ "	             b.updateDate, "
			+ "	             b.discount, "
			+ "	             b.iva, "
			+ "	             b.additionalInformation, "
			+ "	             bbs.id,"
			+ "	             bbs.nameState,"
			+ "              p.id, "
			+ "				 p.rutOrId, "
			+ "				 p.fantasyName) " 
			+ "     FROM BillOfBuy b "
			+ "          inner join b.billOfBuyState bbs "
			+ "          inner join b.provider p ")
	List<BillOfBuyDto> findAllBillOfBuys();
	
}
