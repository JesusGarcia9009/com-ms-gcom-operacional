package com.gcom.operacional.service;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcom.operacional.dto.InventoryTweaksDto;
import com.gcom.operacional.entity.InventoryTweaks;
import com.gcom.operacional.repository.IInventoryTweaksProductRepository;
import com.gcom.operacional.repository.IInventoryTweaksRepository;
import com.gcom.operacional.token.UserPrincipal;
import com.gcom.operacional.utils.Utils;

import lombok.extern.slf4j.Slf4j;

/**
 * InventoryTweaksServiceImpl clase que implementa la interfaz de servicio
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Service
@Slf4j
public class InventoryTweaksServiceImpl implements InventoryTweaksService {

	@Autowired
	private IInventoryTweaksRepository inventoryTweaksRepository;
	
	@Autowired
	private IInventoryTweaksProductRepository inventoryTweaksProductRepository;
	
	@Autowired
	private InventoryTweaksProductService inventoryTweaksProductService;
	
	@Autowired
	private OperationLogService operationLogService;
	
	@Autowired
	private Utils utils;
	
	@Autowired
	private StockService stockService;

	@Override
	public List<InventoryTweaksDto> findAllInventoryTweaks() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<InventoryTweaksDto> result = inventoryTweaksRepository.findAllInventoryTweaks();
		for (InventoryTweaksDto item : result) {
			item.setInventoryTweaksProductsList(inventoryTweaksProductRepository.findInventoryTweaksProductsById(item.getId()));
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	@Override
	public Boolean save(InventoryTweaksDto dto, UserPrincipal token) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));

		InventoryTweaks inventoryTweaksModel = new InventoryTweaks();

		if (Objects.nonNull(dto.getId()))
			inventoryTweaksModel.setId(dto.getId());

		
		inventoryTweaksModel.setUsername(token.getFullName());
		inventoryTweaksModel.setDateInventoryTweaks(new Date());
		inventoryTweaksModel.setReason(dto.getReason());
		inventoryTweaksModel.setAdditionalInformation(dto.getAdditionalInformation());
		inventoryTweaksModel.setInventoryTweaksState("INGRESADO");
		
		inventoryTweaksModel = inventoryTweaksRepository.save(inventoryTweaksModel);
		
		inventoryTweaksProductService.save(dto, inventoryTweaksModel, token);
		
		operationLogService.saveOperationInventory(inventoryTweaksModel.getId(), "INGRESAR" , token);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}
	
	@Override
	public Boolean implement(InventoryTweaksDto dto, UserPrincipal token) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));

		if (Objects.isNull(dto.getId()))
			return Boolean.FALSE;
			
		InventoryTweaks inventoryTweaksModel = utils.validateOpt(inventoryTweaksRepository.findById(dto.getId()));

		inventoryTweaksModel.setInventoryTweaksState("EFECTIVO");
		inventoryTweaksModel.setUsername(token.getFullName());
		inventoryTweaksModel.setDateInventoryTweaks(new Date());
		
		inventoryTweaksModel = inventoryTweaksRepository.save(inventoryTweaksModel);
		
		stockService.saveInventoryTweaks(dto.getInventoryTweaksProductsList(), Boolean.FALSE);
		
		operationLogService.saveOperationInventory(inventoryTweaksModel.getId(), "EFECTIVO" , token);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

	@Override
	public boolean delete(InventoryTweaksDto dto, UserPrincipal token) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		
		if (Objects.isNull(dto.getId()))
			return Boolean.FALSE;
			
		InventoryTweaks inventoryTweaksModel = utils.validateOpt(inventoryTweaksRepository.findById(dto.getId()));

		inventoryTweaksModel.setInventoryTweaksState("REVERSADO");
		inventoryTweaksModel.setUsername(token.getFullName());
		inventoryTweaksModel.setDateInventoryTweaks(new Date());
		
		inventoryTweaksModel = inventoryTweaksRepository.save(inventoryTweaksModel);
		
		stockService.saveInventoryTweaks(dto.getInventoryTweaksProductsList(), Boolean.TRUE);
		
		operationLogService.saveOperationInventory(dto.getId(), "REVERSAR" , token);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

}
