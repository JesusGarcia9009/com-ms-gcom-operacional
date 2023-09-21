package com.gcom.operacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.dto.QuotationAutoCompleteDto;
import com.gcom.operacional.dto.QuotationDto;
import com.gcom.operacional.entity.Quotation;

/**
 * IQuotationRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IQuotationRepository extends CrudRepository<Quotation, Long> {


	@Query(   "   SELECT new com.gcom.operacional.dto.QuotationDto ("
			+ "              q.id,"
			+ "	             q.updateName,"
			+ "              q.updateMail,"
			+ "	             q.updateDate,"
			+ "	             q.generationDate,"
			+ "	             q.discount,"
			+ "	             q.iva,"
			+ "              q.attention, "
			+ "	             q.additionalInformation,"
			+ "              c.id, "
			+ "				 c.rutOrId, "
			+ "				 c.fantasyName, "
			+ "				 qs.id, "
			+ "				 qs.nameState) " 
			+ "     FROM Quotation q "
			+ "          inner join q.quotationState qs "
			+ "          inner join q.client c "
			+ "  ORDER BY q.id desc")
	List<QuotationDto> findAllQuotations();
	
	@Query(   "   SELECT new com.gcom.operacional.dto.QuotationDto ("
			+ "              q.id,"
			+ "	             q.updateName,"
			+ "              q.updateMail,"
			+ "	             q.updateDate,"
			+ "	             q.generationDate,"
			+ "	             q.discount,"
			+ "	             q.iva,"
			+ "              q.attention, "
			+ "	             q.additionalInformation,"
			+ "              c.id, "
			+ "				 c.rutOrId, "
			+ "				 c.fantasyName, "
			+ "				 qs.id, "
			+ "				 qs.nameState) " 
			+ "     FROM Quotation q "
			+ "          inner join q.quotationState qs "
			+ "          inner join q.client c "
			+ "    WHERE q.id = :id ")
	QuotationDto findByIdQ(Long id);
	
	@Query(   "   SELECT new com.gcom.operacional.dto.QuotationAutoCompleteDto ("
			+ "              q.id,"
			+ "              c.id, "
			+ "				 c.rutOrId, "
			+ "				 c.fantasyName) " 
			+ "     FROM Quotation q "
			+ "          inner join q.client c "
			+ "    WHERE :code is null OR :code = '' OR coalesce(CAST(q.id AS text), 'N/A') like '%' || :code || '%' "
			+ " ORDER BY q.id desc ")
	List<QuotationAutoCompleteDto> findQuotationsByCode(String code);
	
}
