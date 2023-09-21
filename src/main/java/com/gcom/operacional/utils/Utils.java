/**
 * 
 */
package com.gcom.operacional.utils;

import static com.gcom.operacional.utils.ConstantUtil.EXCEPTION_DEPENDENCY;
import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gcom.operacional.dto.OrderNoteDto;
import com.gcom.operacional.entity.Client;
import com.gcom.operacional.entity.OrderNote;
import com.gcom.operacional.entity.OrderNoteState;
import com.gcom.operacional.token.UserPrincipal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Utils {
	
//	@Autowired
//	public PlateRepository plateRepository;
	
	@Value("${gcom.iva}")
	private BigDecimal iva;
	
	String MES[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	

	private Utils() {
	}

	public OrderNote getOrderNote(OrderNoteDto orderNote, UserPrincipal token, Client client , OrderNoteState state) {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		OrderNote model = new OrderNote();
		
		Date now = new Date();
		
		if (Objects.nonNull(orderNote.getId()))
			model.setId(orderNote.getId());
		else
			model.setGenerationDate(now);
		
		
	    model.setId(orderNote.getId());
		model.setUpdateName(token.getFullName());
		model.setUpdateDate(now);
		model.setGenerationDate(now);
		model.setDeliveryDate(orderNote.getDeliveryDate());
		model.setDiscount(orderNote.getDiscount());
		model.setIva(this.iva);
		model.setShowAdditionalInformation( orderNote.isShowAdditionalInformation());
		model.setAdditionalInformation( orderNote.getAdditionalInformation());
		model.setNumberOfBill(orderNote.getNumberOfBill());
		model.setDateOfBill(orderNote.getDateOfBill());
		model.setNumberOfPurchaseOrder(orderNote.getNumberOfPurchaseOrder());
		model.setDateOfPurchaseOrder(orderNote.getDateOfPurchaseOrder());
		model.setDeliveryType(orderNote.getDeliveryType());
		model.setTransport(orderNote.getTransport());
		
		model.setClient(client);
		model.setOrderNoteState(state);
		
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return model;
	}
	
	public <T> T validateOpt(Optional<T> opt) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		if (!opt.isPresent()) {
			throw new Exception(EXCEPTION_DEPENDENCY);
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return opt.get();
	}
	
	public String getStringDate(Date date) {
		LocalDate localDate = date.toInstant().atZone(ZoneId.of("UTC")).toLocalDate();
		return (localDate.getDayOfMonth() + " de "+ MES[localDate.getMonthValue()-1] +" de " + localDate.getYear());
	}
	
	
	
	
	
	
	
	

	

}
