package com.gcom.operacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcom.operacional.dto.OrderNoteDto;
import com.gcom.operacional.entity.OrderNote;

/**
 * IOrderNoteRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IOrderNoteRepository extends CrudRepository<OrderNote, Long> {


	@Query(   "   SELECT new com.gcom.operacional.dto.OrderNoteDto ( "
			+ "              o.id, "
			+ "	             o.updateName, "
			+ "	             o.generationDate, "
			+ "	             o.updateDate, "
			+ "	             o.deliveryDate, "
			+ "	             o.discount, "
			+ "	             o.iva, "
			+ "	             o.showAdditionalInformation, "
			+ "	             o.additionalInformation, "
			+ "	             o.numberOfBill, "
			+ "	             o.dateOfBill, "
			+ "	             o.numberOfPurchaseOrder, "
			+ "	             o.dateOfPurchaseOrder, "
			+ "	             c.deliveryType.code, "
			+ "	             c.transport, "
			+ "	             osn.id,"
			+ "	             osn.nameState,"
			+ "              c.id, "
			+ "				 c.rutOrId, "
			+ "				 c.fantasyName) " 
			+ "     FROM OrderNote o "
			+ "          inner join o.orderNoteState osn "
			+ "          inner join o.client c ")
	List<OrderNoteDto> findAllOrderNotes();
	
}
