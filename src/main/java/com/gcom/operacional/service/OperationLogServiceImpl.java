package com.gcom.operacional.service;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcom.operacional.dto.BillOfBuyProductDto;
import com.gcom.operacional.dto.InventoryTweaksProductDto;
import com.gcom.operacional.dto.OperationLogDto;
import com.gcom.operacional.dto.OperationLogRequestDto;
import com.gcom.operacional.dto.OperationTypeDto;
import com.gcom.operacional.dto.OrderNoteProductDto;
import com.gcom.operacional.entity.OperationLog;
import com.gcom.operacional.entity.OperationLogProduct;
import com.gcom.operacional.entity.OperationLogState;
import com.gcom.operacional.entity.Product;
import com.gcom.operacional.enums.OperationTypeEnum;
import com.gcom.operacional.repository.IBillOfBuyProductRepository;
import com.gcom.operacional.repository.IInventoryTweaksProductRepository;
import com.gcom.operacional.repository.IOperationLogProductRepository;
import com.gcom.operacional.repository.IOperationLogRepository;
import com.gcom.operacional.repository.IOperationLogStateRepository;
import com.gcom.operacional.repository.IOrderNoteProductRepository;
import com.gcom.operacional.repository.IProductRepository;
import com.gcom.operacional.token.UserPrincipal;
import com.gcom.operacional.utils.Utils;

import lombok.extern.slf4j.Slf4j;

/**
 * OperationLogServiceImpl clase que implementa la interfaz de servicio
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Service
@Slf4j
public class OperationLogServiceImpl implements OperationLogService {

	@Autowired
	private Utils utils;

	@Autowired
	private IOperationLogRepository operationLogRepository;

	@Autowired
	private IProductRepository productRepository;

	@Autowired
	private IOperationLogProductRepository operationLogProductRepository;

	@Autowired
	private IBillOfBuyProductRepository billOfBuyProductRepository;

	@Autowired
	private IOrderNoteProductRepository orderNoteProductRepository;

	@Autowired
	private IInventoryTweaksProductRepository inventoryTweaksProductRepository;

	@Autowired
	private IOperationLogStateRepository operationLogStateRepository;

	@Override
	public List<OperationLogDto> findAllOperationLog(OperationLogRequestDto request) {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		Calendar calendarStart = Calendar.getInstance();
		calendarStart.setTime(request.getStartDate());
		calendarStart.set(Calendar.HOUR, 00);
		calendarStart.set(Calendar.MINUTE, 00);
		Calendar calendarEnd = Calendar.getInstance();
		calendarEnd.setTime(request.getEndDate());
		calendarEnd.set(Calendar.HOUR, 23);
		calendarEnd.set(Calendar.MINUTE, 59);
		List<OperationLogDto> result = operationLogRepository.findAllOperationLog(calendarStart.getTime(),
				calendarEnd.getTime(), request.getOperationType(), request.getOperationFullName(),
				request.getOperationCurrentState(), request.getOperationIdObject());
		for (OperationLogDto item : result) {
			item.setOperationLogStates(operationLogStateRepository.findOperationLogStateByIdOperationLog(item.getId()));
			item.setOperationLogProducts(operationLogProductRepository.findOperationLogProductByIdOperationLog(item.getId()));
			for (OperationTypeEnum type : OperationTypeEnum.values()) {
				if (type.isEqual(item.getOperationType())) {
					item.setOperationType(type.getDescription());
					break;
				}
			}
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	@Override
	public List<String> findAllUsers() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<String> result = operationLogRepository.findAllUsers();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	@Override
	public List<String> findAllState() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<String> result = operationLogRepository.findAllState();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	@Override
	public List<OperationTypeDto> findAllOperationType() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<OperationTypeDto> result = new ArrayList<>();
		for (OperationTypeEnum type : OperationTypeEnum.values()) {
			result.add(new OperationTypeDto(type.getCode(), type.getDescription()));
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	@Override
	public Boolean saveOperationBillOfBuy(Long idBillOfBuy, String estado, UserPrincipal token) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));

		List<BillOfBuyProductDto> list = billOfBuyProductRepository.findBillOfBuyProductsByIdBillOfBuy(idBillOfBuy);
		OperationLog model = new OperationLog();
		Optional<OperationLog> opt = operationLogRepository.findOperationLog(idBillOfBuy, OperationTypeEnum.FACTURA_COMPRA.getCode());
		if (opt.isPresent())
			model = opt.get();

		model.setOperationDate(new Date());
		model.setOperationFullName(token.getFullName());
		model.setOperationUsername(token.getUsername());
		model.setOperationCurrentState(estado);
		model.setOperationIdObject(idBillOfBuy);
		model.setOperationType(OperationTypeEnum.FACTURA_COMPRA.getCode());

		model = operationLogRepository.save(model);

		OperationLogState modelState = new OperationLogState(null, model, token.getUsername(), token.getFullName(),
				new Date(), estado);
		operationLogStateRepository.save(modelState);

		operationLogProductRepository
				.deleteAll(operationLogProductRepository.findOperationLogProductByIdOperation(model.getId()));
		for (BillOfBuyProductDto item : list) {
			Product p = utils.validateOpt(productRepository.findById(item.getProductId()));
			OperationLogProduct olp = new OperationLogProduct();
			olp.setProduct(p);
			olp.setOperationLog(model);
			olp.setAmount(item.getAmount().longValue() * (-1));
			operationLogProductRepository.save(olp);
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

	@Override
	public Boolean saveOperationOrderNote(Long idOrderNote, String estado, UserPrincipal token) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));

		List<OrderNoteProductDto> list = orderNoteProductRepository.findOrderNoteProductsByIdOrderNote(idOrderNote);
		OperationLog model = new OperationLog();
		Optional<OperationLog> opt = operationLogRepository.findOperationLog(idOrderNote, OperationTypeEnum.NOTA_PEDIDO.getCode());
		if (opt.isPresent())
			model = opt.get();

		model.setOperationDate(new Date());
		model.setOperationFullName(token.getFullName());
		model.setOperationUsername(token.getUsername());
		model.setOperationCurrentState(estado);
		model.setOperationIdObject(idOrderNote);
		model.setOperationType(OperationTypeEnum.NOTA_PEDIDO.getCode());

		model = operationLogRepository.save(model);

		OperationLogState modelState = new OperationLogState(null, model, token.getUsername(), token.getFullName(),
				new Date(), estado);
		operationLogStateRepository.save(modelState);

		operationLogProductRepository
				.deleteAll(operationLogProductRepository.findOperationLogProductByIdOperation(model.getId()));
		for (OrderNoteProductDto item : list) {
			Product p = utils.validateOpt(productRepository.findById(item.getProductId()));
			OperationLogProduct olp = new OperationLogProduct();
			olp.setProduct(p);
			olp.setOperationLog(model);
			olp.setAmount(item.getAmount().longValue());
			operationLogProductRepository.save(olp);
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

	@Override
	public Boolean saveOperationInventory(Long idInventoryTweaks, String estado, UserPrincipal token) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));

		List<InventoryTweaksProductDto> list = inventoryTweaksProductRepository
				.findInventoryTweaksProductsById(idInventoryTweaks);
		OperationLog model = new OperationLog();
		Optional<OperationLog> opt = operationLogRepository.findOperationLog(idInventoryTweaks, OperationTypeEnum.AJUSTE_INVENTARIO.getCode());
		if (opt.isPresent())
			model = opt.get();

		model.setOperationDate(new Date());
		model.setOperationFullName(token.getFullName());
		model.setOperationUsername(token.getUsername());
		model.setOperationCurrentState(estado);
		model.setOperationIdObject(idInventoryTweaks);
		model.setOperationType(OperationTypeEnum.AJUSTE_INVENTARIO.getCode());

		model = operationLogRepository.save(model);

		OperationLogState modelState = new OperationLogState(null, model, token.getUsername(), token.getFullName(),
				new Date(), estado);
		operationLogStateRepository.save(modelState);

		operationLogProductRepository
				.deleteAll(operationLogProductRepository.findOperationLogProductByIdOperation(model.getId()));
		for (InventoryTweaksProductDto item : list) {
			Product p = utils.validateOpt(productRepository.findById(item.getProductId()));
			OperationLogProduct olp = new OperationLogProduct();
			olp.setProduct(p);
			olp.setOperationLog(model);
			olp.setAmount(item.getAmount().longValue());
			operationLogProductRepository.save(olp);
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

}
