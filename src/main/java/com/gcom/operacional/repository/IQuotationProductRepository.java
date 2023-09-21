package com.gcom.operacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.dto.QuotationProductDto;
import com.gcom.operacional.entity.QuotationProduct;

/**
 * IQuotationProductRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IQuotationProductRepository extends CrudRepository<QuotationProduct, Long> {

	@Query(   "   SELECT new com.gcom.operacional.dto.QuotationProductDto ("
			+ "              qp.id,"
			+ "	             qp.amount,"
			+ "	             qp.salePrice,"
			+ "				 qp.delivery, "
			+ "	             p.id,"
			+ "	             p.description,"
			+ "              b.code || '-' || g.code || '-' || m.code || '-' || s.code ) "
			+ "     FROM Quotation q "
			+ "          inner join q.quotationProducts qp "
			+ "			 inner join qp.product p "
			+ "			 inner join p.model m "
			+ "			 inner join m.brand b "
			+ "			 inner join p.universalGroups g "
			+ "			 inner join p.sources s "
			+ "    WHERE q.id = :idQuotation")
	List<QuotationProductDto> findQuotationProductsByIdQuotation(Long idQuotation);
	
	@Query(   "   SELECT new com.gcom.operacional.dto.QuotationProductDto ("
			+ "              qp.id,"
			+ "	             qp.amount,"
			+ "	             qp.salePrice,"
			+ "				 qp.delivery, "
			+ "	             p.id,"
			+ "	             p.description,"
			+ "              b.code || '-' || g.code || '-' || m.code || '-' || s.code  ,"
			+ "              p.rowNumb,"
			+ "              p.colNumb ) " 
			+ "     FROM Quotation q "
			+ "          inner join q.quotationProducts qp "
			+ "			 inner join qp.product p "
			+ "			 inner join p.model m "
			+ "			 inner join m.brand b "
			+ "			 inner join p.universalGroups g "
			+ "			 inner join p.sources s "
			+ "    WHERE q.id = :idQuotation")
	List<QuotationProductDto> findQuotationProductsByIdQuotationWithRowsColls(Long idQuotation);
	
	@Query(   "   SELECT qp " 
			+ "     FROM Quotation q "
			+ "          inner join q.quotationProducts qp "
			+ "			 inner join qp.product p "
			+ "			 inner join p.model m "
			+ "			 inner join m.brand b "
			+ "			 inner join p.universalGroups g "
			+ "			 inner join p.sources s "
			+ "    WHERE q.id = :idQuotation")
	List<QuotationProduct> findQuotProdByIdQuotation(Long idQuotation);
}
