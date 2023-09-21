package com.gcom.operacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.dto.ProviderDto;
import com.gcom.operacional.entity.Provider;

/**
 * IProviderRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IProviderRepository extends CrudRepository<Provider, Long> {


	@Query(   "   SELECT new com.gcom.operacional.dto.ProviderDto ("
			+ "              p.id,"
			+ "              p.rutOrId,"
			+ "              p.fantasyName,"
			+ "              p.businessName,"
			+ "              p.address,"
			+ "              p.transport,"
			+ "              p.deliveryObservation,"
			+ "              p.attachedDocument,"
			+ "              co.id, "
			+ "				 co.contactName, "
			+ "				 co.phone, "
			+ "				 co.cellPhone, "
			+ "				 co.businessMail,"
			+ "				 prov.id, "
			+ "				 prov.description, "
			+ "				 rc.id, "
			+ "				 rc.description, "
			+ "				 country.id , "
			+ "				 country.description ) " 
			+ "     FROM Provider p "
			+ "          inner join p.contact co "
			+ "			 inner join p.provinceOrState prov "
			+ "			 inner join prov.regionOrCity rc "
			+ "			 inner join rc.country country ")
	List<ProviderDto> findAllProviders();
	
	@Query(   "   SELECT new com.gcom.operacional.dto.ProviderDto ("
			+ "              p.id,"
			+ "              p.rutOrId,"
			+ "              p.fantasyName,"
			+ "              p.businessName,"
			+ "              p.address,"
			+ "              p.transport,"
			+ "              p.deliveryObservation,"
			+ "              p.attachedDocument,"
			+ "              co.id, "
			+ "				 co.contactName, "
			+ "				 co.phone, "
			+ "				 co.cellPhone, "
			+ "				 co.businessMail,"
			+ "				 prov.id, "
			+ "				 prov.description, "
			+ "				 rc.id, "
			+ "				 rc.description, "
			+ "				 country.id , "
			+ "				 country.description ) " 
			+ "     FROM Provider p "
			+ "          inner join p.contact co "
			+ "			 inner join p.provinceOrState prov "
			+ "			 inner join prov.regionOrCity rc "
			+ "			 inner join rc.country country "
			+ "    WHERE p.id = :id")
	ProviderDto findProviderById(Long id);
	
	@Query(   "   SELECT p FROM Provider p where p.rutOrId = :rutOrId ")
	Provider findByRut(String rutOrId);
	
}
