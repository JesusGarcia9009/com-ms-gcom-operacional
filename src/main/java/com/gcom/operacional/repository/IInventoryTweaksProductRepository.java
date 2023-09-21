package com.gcom.operacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.dto.InventoryTweaksProductDto;
import com.gcom.operacional.entity.InventoryTweaksProduct;

/**
 * IInventoryTweaksReasonRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IInventoryTweaksProductRepository extends CrudRepository<InventoryTweaksProduct, Long> {


	@Query(   "   SELECT new com.gcom.operacional.dto.InventoryTweaksProductDto ("
			+ "              itp.id,"
			+ "	             itp.amount,"
			+ "	             p.id,"
			+ "	             p.description,"
			+ "              m.code || '-' || g.code || '-' || b.code || '-' || s.code ,"
			+ "				 p.rowNumb, "
			+ "			     p.colNumb) " 
			+ "     FROM InventoryTweaks it "
			+ "          inner join it.inventoryTweaksProducts itp "
			+ "			 inner join itp.product p "
			+ "			 inner join p.model m "
			+ "			 inner join m.brand b "
			+ "			 inner join p.universalGroups g "
			+ "			 inner join p.sources s "
			+ "    WHERE it.id = :idInventoryTweaks")
	List<InventoryTweaksProductDto> findInventoryTweaksProductsById(Long idInventoryTweaks);
	
	@Query(   "   SELECT itp " 
			+ "     FROM InventoryTweaks it "
			+ "          inner join it.inventoryTweaksProducts itp "
			+ "			 inner join itp.product p "
			+ "			 inner join p.model m "
			+ "			 inner join m.brand b "
			+ "			 inner join p.universalGroups g "
			+ "			 inner join p.sources s "
			+ "    WHERE it.id = :idInventoryTweaks")
	List<InventoryTweaksProduct> findInventoryTweaksProductsByIdInventoryTweaks(Long idInventoryTweaks);
	
}
