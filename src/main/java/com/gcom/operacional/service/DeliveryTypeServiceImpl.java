package com.gcom.operacional.service;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;
import static com.gcom.operacional.utils.ConstantUtil.MSG_DELIVERY_TYPE_DUPL;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcom.operacional.dto.DeliveryTypeDto;
import com.gcom.operacional.entity.DeliveryType;
import com.gcom.operacional.repository.IDeliveryTypeRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * DeliveryTypeServiceImpl clase que implementa la interfaz de servicio
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Service
@Slf4j
public class DeliveryTypeServiceImpl implements DeliveryTypeService {

	@Autowired
	private IDeliveryTypeRepository deliveryTypeRepository;

	@Override
	public List<DeliveryTypeDto> getDeliveryTypeList() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<DeliveryTypeDto> result = deliveryTypeRepository.findAllDeliveryType();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	@Override
	public Boolean save(DeliveryTypeDto deliveryType) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		DeliveryType model = deliveryTypeRepository.findByCode(deliveryType.getCode());
		
		if ((Objects.nonNull(model) && Objects.isNull(deliveryType.getId()))
				|| Objects.nonNull(model) && !deliveryType.getId().equals(model.getId()))
			throw new Exception(MSG_DELIVERY_TYPE_DUPL);

		    DeliveryType dtModel = new DeliveryType();
		
			if(Objects.nonNull(deliveryType.getId()))
				dtModel.setId(deliveryType.getId());
				
			dtModel.setCode(deliveryType.getCode());
			dtModel.setDescription(deliveryType.getDescription());
			
			deliveryTypeRepository.save(dtModel);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

	@Override
	public boolean delete(DeliveryTypeDto dto) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		deliveryTypeRepository.deleteById(dto.getId());
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

}
