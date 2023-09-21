package com.gcom.operacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.dto.ClientDto;
import com.gcom.operacional.entity.Client;

/**
 * IUserRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IClientRepository extends CrudRepository<Client, Long> {


	@Query(   "   SELECT new com.gcom.operacional.dto.ClientDto ("
			+ "              c.id, "
			+ "				 c.rutOrId, "
			+ "				 c.fantasyName, "
			+ "				 c.businessName, "
			+ "				 c.address, "
			+ "				 c.transport, "
			+ "				 c.deliveryObservation, "
			+ "				 c.attachedDocument,"
			+ "              co.id, "
			+ "				 co.contactName, "
			+ "				 co.phone, "
			+ "				 co.cellPhone, "
			+ "				 co.businessMail,"
			+ "              dt.id, "
			+ "              dt.code, "
			+ "              dt.description, "
			+ "              pm.id, "
			+ "              pm.initials, "
			+ "              pm.description, "
			+ "              pm.days, "
			+ "				 prov.id, "
			+ "				 prov.description, "
			+ "				 rc.id, "
			+ "				 rc.description, "
			+ "				 country.id , "
			+ "				 country.description ) " 
			+ "     FROM Client c "
			+ "          inner join c.contact co "
			+ "			 inner join c.deliveryType dt "
			+ "			 inner join c.paymentMethod pm "
			+ "			 inner join c.provinceOrState prov "
			+ "			 inner join prov.regionOrCity rc "
			+ "			 inner join rc.country country ")
	List<ClientDto> findAllClients();
	
	@Query(   "   SELECT new com.gcom.operacional.dto.ClientDto ("
			+ "              c.id, "
			+ "				 c.rutOrId, "
			+ "				 c.fantasyName, "
			+ "				 c.businessName, "
			+ "				 c.address, "
			+ "				 c.transport, "
			+ "				 c.deliveryObservation, "
			+ "				 c.attachedDocument,"
			+ "              co.id, "
			+ "				 co.contactName, "
			+ "				 co.phone, "
			+ "				 co.cellPhone, "
			+ "				 co.businessMail,"
			+ "              dt.id, "
			+ "              dt.code, "
			+ "              dt.description, "
			+ "              pm.id, "
			+ "              pm.initials, "
			+ "              pm.description, "
			+ "              pm.days, "
			+ "				 prov.id, "
			+ "				 prov.description, "
			+ "				 rc.id, "
			+ "				 rc.description, "
			+ "				 country.id , "
			+ "				 country.description ) " 
			+ "     FROM Client c "
			+ "          inner join c.contact co "
			+ "			 inner join c.deliveryType dt "
			+ "			 inner join c.paymentMethod pm "
			+ "			 inner join c.provinceOrState prov "
			+ "			 inner join prov.regionOrCity rc "
			+ "			 inner join rc.country country "
			+ "    WHERE c.id = :id ")
	ClientDto findClientById(Long id);
	
	@Query(   "   SELECT c " 
			+ "     FROM Client c where c.rutOrId = :rutOrId ")
	Client findByRut(String rutOrId);
	
}
