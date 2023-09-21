package com.gcom.operacional.service;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;
import static com.gcom.operacional.utils.ConstantUtil.MSG_QUOTATION_DELIVERY_DUPL;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcom.operacional.dto.QuotationDeliveryDto;
import com.gcom.operacional.entity.QuotationDelivery;
import com.gcom.operacional.repository.IQuotationDeliveryRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * QuotationDeliveryServiceImpl clase que implementa la interfaz de servicio
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Service
@Slf4j
public class QuotationDeliveryServiceImpl implements QuotationDeliveryService {

	@Autowired
	private IQuotationDeliveryRepository quotationDeliveryRepository;
	
	@Override
	public List<QuotationDeliveryDto> findAllQuotationDelivery() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<QuotationDeliveryDto> result = quotationDeliveryRepository.findAllQuotationDelivery();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	@Override
	public Boolean save(QuotationDeliveryDto quotationDelivery) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		QuotationDelivery model = quotationDeliveryRepository.findByDescription(quotationDelivery.getDescription());
		
		if ((Objects.nonNull(model) && Objects.isNull(quotationDelivery.getId()))
				|| Objects.nonNull(model) && !quotationDelivery.getId().equals(model.getId()))
			throw new Exception(MSG_QUOTATION_DELIVERY_DUPL);

		    QuotationDelivery quotationDeliveryModel = new QuotationDelivery();
		
			if(Objects.nonNull(quotationDelivery.getId()))
				quotationDeliveryModel.setId(quotationDelivery.getId());
				
			quotationDeliveryModel.setDescription(quotationDelivery.getDescription());
			quotationDeliveryRepository.save(quotationDeliveryModel);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

	@Override
	public boolean delete(QuotationDeliveryDto dto) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		quotationDeliveryRepository.deleteById(dto.getId());
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

}
