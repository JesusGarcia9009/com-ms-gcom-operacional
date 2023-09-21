package com.gcom.operacional.service;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcom.operacional.dto.InventoryTweaksProductDto;
import com.gcom.operacional.entity.BillOfBuyProduct;
import com.gcom.operacional.entity.OrderNoteProduct;
import com.gcom.operacional.entity.Product;
import com.gcom.operacional.repository.IProductRepository;
import com.gcom.operacional.utils.Utils;

import lombok.extern.slf4j.Slf4j;

/**
 * StockServiceImpl clase que implementa la interfaz de servicio
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Service
@Slf4j
public class StockServiceImpl implements StockService {

	@Autowired
	private IProductRepository productRepository;

	@Autowired
	private Utils utils;

	@Override
	@Transactional
	public void saveBill(List<BillOfBuyProduct> list, Boolean isReverse) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<Product> temp = new ArrayList<>();
		//factura suma 
		Integer operation = isReverse ? -1 : 1;
		for (BillOfBuyProduct item : list) {
			Product model = this.saveProduct(item.getProduct().getId(), item.getAmount().intValue(), operation);
			temp.add(model);
		}
		productRepository.saveAll(temp);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
	}

	@Override
	@Transactional
	public void saveOrderNote(List<OrderNoteProduct> list, Boolean isReverse) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<Product> temp = new ArrayList<>();
		Integer operation = isReverse ? 1 : -1;
		for (OrderNoteProduct item : list) {
			Product model = this.saveProduct(item.getProduct().getId(), item.getAmount().intValue(), operation);
			temp.add(model);
		}
		productRepository.saveAll(temp);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
	}

	@Override
	@Transactional
	public void saveInventoryTweaks(List<InventoryTweaksProductDto> list, Boolean isReverse) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<Product> temp = new ArrayList<>();
		Integer operation = isReverse ? -1 : 1;
		for (InventoryTweaksProductDto item : list) {
			Product model = this.saveProduct(item.getProductId(), item.getAmount(), operation);
			temp.add(model);
		}
		productRepository.saveAll(temp);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
	}

	@Override
	public Product saveProduct(Long idProduct, Integer amount, Integer operation) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		Product model = utils.validateOpt(productRepository.findById(idProduct));
		Integer a = amount * operation;
		model.setStock(model.getStock() + a);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return model;
	}

}
