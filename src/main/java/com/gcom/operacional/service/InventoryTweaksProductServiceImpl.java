package com.gcom.operacional.service;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcom.operacional.dto.InventoryTweaksDto;
import com.gcom.operacional.dto.InventoryTweaksProductDto;
import com.gcom.operacional.entity.InventoryTweaks;
import com.gcom.operacional.entity.InventoryTweaksProduct;
import com.gcom.operacional.entity.Product;
import com.gcom.operacional.repository.IInventoryTweaksProductRepository;
import com.gcom.operacional.repository.IProductRepository;
import com.gcom.operacional.token.UserPrincipal;

import lombok.extern.slf4j.Slf4j;

/**
 * InventoryTweaksProductServiceImpl clase que implementa la interfaz de servicio
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Service
@Slf4j
public class InventoryTweaksProductServiceImpl implements InventoryTweaksProductService {

	@Autowired
	private IInventoryTweaksProductRepository inventoryTweaksProductRepository;
	
	@Autowired
	private IProductRepository productRepository;
	

	@Override
	public Boolean save(InventoryTweaksDto dto, InventoryTweaks model, UserPrincipal token) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));

		List<InventoryTweaksProduct> inventoryTweaksProductList = new ArrayList<>();
		inventoryTweaksProductRepository.deleteAll(inventoryTweaksProductRepository.findInventoryTweaksProductsByIdInventoryTweaks(model.getId()));
		
		for (InventoryTweaksProductDto item : dto.getInventoryTweaksProductsList()) {
			Optional<Product> optProduct = productRepository.findById(item.getProductId());
			InventoryTweaksProduct temp = new InventoryTweaksProduct();
			temp.setId(item.getId());
			temp.setAmount(item.getAmount());
			temp.setProduct(optProduct.get());
			temp.setInventoryTweaks(model);
			inventoryTweaksProductList.add(temp);
		}
		inventoryTweaksProductRepository.saveAll(inventoryTweaksProductList);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}
	
}
