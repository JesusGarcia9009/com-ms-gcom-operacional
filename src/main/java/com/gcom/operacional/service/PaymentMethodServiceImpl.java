package com.gcom.operacional.service;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;
import static com.gcom.operacional.utils.ConstantUtil.MSG_PAYMENT_METHOD_DUPL;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcom.operacional.dto.PaymentMethodDto;
import com.gcom.operacional.entity.PaymentMethod;
import com.gcom.operacional.repository.IPaymentMethodRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * PaymentMethodServiceImpl clase que implementa la interfaz de servicio
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Service
@Slf4j
public class PaymentMethodServiceImpl implements PaymentMethodService {

	@Autowired
	private IPaymentMethodRepository paymentMethodRepository;

	@Override
	public List<PaymentMethodDto> getPaymentMethodList() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<PaymentMethodDto> result = paymentMethodRepository.findAllPaymentMethod();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}
	
	@Override
	public PaymentMethodDto findPaymentMethodById(Long id) {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		PaymentMethodDto result = paymentMethodRepository.findPaymentMethodById(id);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	@Override
	public Boolean save(PaymentMethodDto dto) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		PaymentMethod model = paymentMethodRepository.findByInitials(dto.getInitials());
		
		if ((Objects.nonNull(model) && Objects.isNull(dto.getId()))
				|| Objects.nonNull(model) && !dto.getId().equals(model.getId()))
			throw new Exception(MSG_PAYMENT_METHOD_DUPL);

		    PaymentMethod pmModel = new PaymentMethod();
		
			if(Objects.nonNull(dto.getId()))
				pmModel.setId(dto.getId());
				
			pmModel.setInitials(dto.getInitials());
			pmModel.setDescription(dto.getDescription());
			pmModel.setDays(dto.getDays());
			
			paymentMethodRepository.save(pmModel);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

	@Override
	public boolean delete(PaymentMethodDto dto) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		paymentMethodRepository.deleteById(dto.getId());
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

	

}
