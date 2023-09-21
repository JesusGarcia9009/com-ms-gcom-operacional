package com.gcom.operacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.dto.OrderNoteProductDto;
import com.gcom.operacional.entity.OrderNoteProduct;

/**
 * IOrderNoteProductRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IOrderNoteProductRepository extends CrudRepository<OrderNoteProduct, Long> {

	@Query(   "   SELECT new com.gcom.operacional.dto.OrderNoteProductDto ("
			+ "              onp.id,"
			+ "	             onp.amount,"
			+ "	             onp.salePrice,"
			+ "	             p.id,"
			+ "	             p.description,"
			+ "              b.code || '-' || g.code || '-' || m.code || '-' || s.code ) " 
			+ "     FROM OrderNote o "
			+ "          inner join o.orderNoteProducts onp "
			+ "			 inner join onp.product p "
			+ "			 inner join p.model m "
			+ "			 inner join m.brand b "
			+ "			 inner join p.universalGroups g "
			+ "			 inner join p.sources s "
			+ "    WHERE o.id = :idOrderNote")
	List<OrderNoteProductDto> findOrderNoteProductsByIdOrderNote(Long idOrderNote);
	
	@Query(   "   SELECT new com.gcom.operacional.dto.OrderNoteProductDto ( "
			+ "              onp.id,"
			+ "	             onp.amount,"
			+ "	             onp.salePrice, "
			+ "	             p.id,"
			+ "	             p.description,"
			+ "              b.code || '-' || g.code || '-' || m.code || '-' || s.code,"
			+ "				 p.rowNumb,"
			+ "              p.colNumb ) " 
			+ "     FROM OrderNote o "
			+ "          inner join o.orderNoteProducts onp "
			+ "			 inner join onp.product p "
			+ "			 inner join p.model m "
			+ "			 inner join m.brand b "
			+ "			 inner join p.universalGroups g "
			+ "			 inner join p.sources s "
			+ "    WHERE o.id = :idOrderNote")
	List<OrderNoteProductDto> findOrderNoteProductsByIdOrderNoteWithRowsColls(Long idOrderNote);
	
	@Query(   "   SELECT onp " 
			+ "     FROM OrderNote o "
			+ "          inner join o.orderNoteProducts onp "
			+ "			 inner join onp.product p "
			+ "			 inner join p.model m "
			+ "			 inner join m.brand b "
			+ "			 inner join p.universalGroups g "
			+ "			 inner join p.sources s "
			+ "    WHERE o.id = :idOrderNote")
	List<OrderNoteProduct> findOrderNoteProductByIdOrderNote(Long idOrderNote);
}
