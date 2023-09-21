package com.gcom.operacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.dto.ModelDto;
import com.gcom.operacional.entity.Model;

/**
 * IModelRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IModelRepository extends CrudRepository<Model, Long> {


	@Query(   "   SELECT new com.gcom.operacional.dto.ModelDto ( "
			+ "              m.id, "
			+ "				 m.code, "
			+ "				 m.description, "
			+ "				 m.measure, "
			+ "				 m.vehicleType, "
			+ "				 m.approximateYear, "
			+ "				 m.engineDescription, "
			+ "				 m.typeOfMotor, "
			+ "				 m.notes, "
			+ "				 m.mast, "
			+ "				 b.id, "
			+ "				 b.code, "
			+ "				 b.description) " 
			+ "     FROM Model m inner join m.brand b where (:idBrand is null OR b.id = :idBrand ) ")
	List<ModelDto> findModelsByBrand(Long idBrand);
	
	Model findByCode(String code);
	
}
