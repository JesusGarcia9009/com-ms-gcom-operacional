package com.gcom.operacional.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.dto.ProductDto;
import com.gcom.operacional.dto.ProductSelectDto;
import com.gcom.operacional.entity.Product;

/**
 * IProductRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IProductRepository extends CrudRepository<Product, Long> {


	@Query(   "   SELECT new com.gcom.operacional.dto.ProductDto ("
			+ "              p.id, "
			+ "				 p.salePrice, "
			+ "				 p.netCost, "
			+ "				 p.stock, "
			+ "				 p.rowNumb, "
			+ "				 p.colNumb, "
			+ "				 p.description, "
			+ "				 p.providerDescription, "
			+ "              m.id, "
			+ "	             m.code, "
			+ "	             m.description, "
			+ "	             m.measure, "
			+ "				 m.vehicleType, "
			+ "				 m.approximateYear, "
			+ "				 m.engineDescription, "
			+ "				 m.typeOfMotor, "
			+ "				 m.notes, "
			+ "				 m.mast,     "
			+ " 			 b.id, "
			+ "				 b.code, "
			+ "				 b.description, "
			+ " 			 pr.id, "
			+ " 			 (br.code || '-' || ugr.code || '-' || mr.code || '-' || sr.code), "
			+ " 			 pr.description, "
			+ " 			 pt.id, "
			+ "				 pt.code, "
			+ "				 pt.description,"
			+ " 			 s.id, "
			+ "				 s.code, "
			+ "				 s.description,"
			+ " 			 ug.id, "
			+ "				 ug.code, "
			+ "				 ug.description) " 
			+ "     FROM Product p "
			+ "          left join p.product pr "
			+ "          left join pr.model mr "
			+ "			 left join mr.brand br "
			+ "			 left join pr.sources sr "
			+ "			 left join pr.universalGroups ugr "
			+ "          inner join p.model m "
			+ "			 inner join m.brand b "
			+ "			 inner join p.productType pt "
			+ "			 inner join p.sources s "
			+ "			 inner join p.universalGroups ug "
			+ "    ORDER BY br.code , ugr.code , mr.code , sr.code")
	List<ProductDto> findAllProducts();
	
	@Query(   "   SELECT new com.gcom.operacional.dto.ProductSelectDto ("
			+ "              p.id, "
			+ "				 p.salePrice, "
			+ "				 p.netCost, "
			+ "				 p.stock, "
			+ "				 p.rowNumb, "
			+ "				 p.colNumb, "
			+ "				 p.description, "
			+ "				 p.providerDescription, "
			+ "              m.id, "
			+ "	             m.code, "
			+ "	             m.description, "
			+ "	             m.measure, "
			+ "				 m.vehicleType, "
			+ "				 m.approximateYear, "
			+ "				 m.engineDescription, "
			+ "				 m.typeOfMotor, "
			+ "				 m.notes, "
			+ "				 m.mast,     "
			+ " 			 b.id, "
			+ "				 b.code, "
			+ "				 b.description, "
			+ " 			 pr.id, "
			+ " 			 (br.code || '-' || ugr.code || '-' || mr.code || '-' || sr.code), "
			+ " 			 pr.description, "
			+ " 			 pt.id, "
			+ "				 pt.code, "
			+ "				 pt.description,"
			+ " 			 s.id, "
			+ "				 s.code, "
			+ "				 s.description,"
			+ " 			 ug.id, "
			+ "				 ug.code, "
			+ "				 ug.description) " 
			+ "     FROM Product p "
			+ "          left join p.product pr "
			+ "          left join pr.model mr "
			+ "			 left join mr.brand br "
			+ "			 left join pr.sources sr "
			+ "			 left join pr.universalGroups ugr "
			+ "          inner join p.model m "
			+ "			 inner join m.brand b "
			+ "			 inner join p.productType pt "
			+ "			 inner join p.sources s "
			+ "			 inner join p.universalGroups ug ")
	List<ProductSelectDto> findAllProductSelect();
	
	@Query(   "   SELECT new com.gcom.operacional.dto.ProductDto ("
			+ "              p.id, "
			+ "				 p.salePrice, "
			+ "				 p.netCost, "
			+ "				 p.stock, "
			+ "				 p.rowNumb, "
			+ "				 p.colNumb, "
			+ "				 p.description, "
			+ "				 p.providerDescription, "
			+ "              m.id, "
			+ "	             m.code, "
			+ "	             m.description, "
			+ "	             m.measure, "
			+ "				 m.vehicleType, "
			+ "				 m.approximateYear, "
			+ "				 m.engineDescription, "
			+ "				 m.typeOfMotor, "
			+ "				 m.notes, "
			+ "				 m.mast,     "
			+ " 			 b.id, "
			+ "				 b.code, "
			+ "				 b.description, "
			+ " 			 pr.id, "
			+ " 			 (br.code || '-' || ugr.code || '-' || mr.code || '-' || sr.code), "
			+ " 			 pr.description, "
			+ " 			 pt.id, "
			+ "				 pt.code, "
			+ "				 pt.description,"
			+ " 			 s.id, "
			+ "				 s.code, "
			+ "				 s.description,"
			+ " 			 ug.id, "
			+ "				 ug.code, "
			+ "				 ug.description) " 
			+ "     FROM Product p "
			+ "          left join p.product pr "
			+ "          left join pr.model mr "
			+ "			 left join mr.brand br "
			+ "			 left join pr.sources sr "
			+ "			 left join pr.universalGroups ugr "
			+ "          inner join p.model m "
			+ "			 inner join m.brand b "
			+ "			 inner join p.productType pt "
			+ "			 inner join p.sources s "
			+ "			 inner join p.universalGroups ug "
			+ "    WHERE p.id = :id ")
	public ProductDto findByIdProduct(Long id);
	
	@Query(" SELECT g.description FROM Gloss g WHERE g.product.id = :idProduct  ")
	List<String> findGlossList(Long idProduct);
	
	@Query(" SELECT g.description FROM OriginalCode g WHERE g.product.id = :idProduct ")
	List<String> findOriginalCodeList(Long idProduct);
	
	@Query(" SELECT g.description FROM ProviderCode g WHERE g.product.id = :idProduct ")
	List<String> findProviderCodeList(Long idProduct);
	
	
	Product findByNetCostAndSalePriceAndDescription(BigDecimal netCost, BigDecimal salePrice, String description);
	
}
