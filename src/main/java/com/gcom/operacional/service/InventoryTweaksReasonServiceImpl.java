package com.gcom.operacional.service;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;
import static com.gcom.operacional.utils.ConstantUtil.MSG_INVENTORY_REASON_DUPL;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcom.operacional.dto.InventoryTweaksReasonDto;
import com.gcom.operacional.entity.InventoryTweaksReason;
import com.gcom.operacional.repository.IInventoryTweaksReasonRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * InventoryTweaksReasonServiceImpl clase que implementa la interfaz de servicio
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Service
@Slf4j
public class InventoryTweaksReasonServiceImpl implements InventoryTweaksReasonService {

	@Autowired
	private IInventoryTweaksReasonRepository inventoryTweaksReasonRepository;
	
	@Override
	public List<InventoryTweaksReasonDto> findAllInventoryTweaksReason() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<InventoryTweaksReasonDto> result = inventoryTweaksReasonRepository.findAllInventoryTweaksReason();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	@Override
	public Boolean save(InventoryTweaksReasonDto dto) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		InventoryTweaksReason model = inventoryTweaksReasonRepository.findByDescription(dto.getDescription());
		
		if ((Objects.nonNull(model) && Objects.isNull(dto.getId()))
				|| Objects.nonNull(model) && !dto.getId().equals(model.getId()))
			throw new Exception(MSG_INVENTORY_REASON_DUPL);

		    InventoryTweaksReason inventoryTweaksReasonModel = new InventoryTweaksReason();
		
			if(Objects.nonNull(dto.getId()))
				inventoryTweaksReasonModel.setId(dto.getId());
				
			inventoryTweaksReasonModel.setDescription(dto.getDescription());
			inventoryTweaksReasonRepository.save(inventoryTweaksReasonModel);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

	@Override
	public boolean delete(InventoryTweaksReasonDto dto) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		inventoryTweaksReasonRepository.deleteById(dto.getId());
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

}
