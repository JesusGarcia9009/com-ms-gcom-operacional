package com.gcom.operacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.dto.BillOfBuyProductDto;
import com.gcom.operacional.entity.BillOfBuyProduct;

/**
 * IBillOfBuyProductRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IBillOfBuyProductRepository extends CrudRepository<BillOfBuyProduct, Long> {

	@Query(   "   SELECT new com.gcom.operacional.dto.BillOfBuyProductDto ("
			+ "              bbp.id,"
			+ "	             bbp.amount,"
			+ "	             bbp.salePrice,"
			+ "	             p.id,"
			+ "	             p.description,"
			+ "              m.code || '-' || g.code || '-' || b.code || '-' || s.code ) " 
			+ "     FROM BillOfBuy bb "
			+ "          inner join bb.billOfBuyProducts bbp "
			+ "			 inner join bbp.product p "
			+ "			 inner join p.model m "
			+ "			 inner join m.brand b "
			+ "			 inner join p.universalGroups g "
			+ "			 inner join p.sources s "
			+ "    WHERE bb.id = :idBillOfBuy")
	List<BillOfBuyProductDto> findBillOfBuyProductsByIdBillOfBuy(Long idBillOfBuy);
	
	@Query(   "   SELECT new com.gcom.operacional.dto.BillOfBuyProductDto ( "
			+ "              bbp.id,"
			+ "	             bbp.amount,"
			+ "	             bbp.salePrice, "
			+ "	             p.id,"
			+ "	             p.description,"
			+ "              m.code || '-' || g.code || '-' || b.code || '-' || s.code,"
			+ "			     p.rowNumb,"
			+ "              p.colNumb ) " 
			+ "     FROM BillOfBuy bb "
			+ "          inner join bb.billOfBuyProducts bbp "
			+ "			 inner join bbp.product p "
			+ "			 inner join p.model m "
			+ "			 inner join m.brand b "
			+ "			 inner join p.universalGroups g "
			+ "			 inner join p.sources s "
			+ "    WHERE bb.id = :idBillOfBuy")
	List<BillOfBuyProductDto> findBillOfBuyProductByIdBillOfBuyWithRowsColls(Long idBillOfBuy);
	
	@Query(   "   SELECT bbp " 
			+ "     FROM BillOfBuy bb "
			+ "          inner join bb.billOfBuyProducts bbp "
			+ "			 inner join bbp.product p "
			+ "			 inner join p.model m "
			+ "			 inner join m.brand b "
			+ "			 inner join p.universalGroups g "
			+ "			 inner join p.sources s "
			+ "    WHERE bb.id = :idBillOfBuy")
	List<BillOfBuyProduct> findBillProductsByIdBillOfBuy(Long idBillOfBuy);
	
}
