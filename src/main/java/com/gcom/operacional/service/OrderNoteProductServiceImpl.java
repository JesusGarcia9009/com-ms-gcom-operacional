package com.gcom.operacional.service;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcom.operacional.dto.OrderNoteDto;
import com.gcom.operacional.dto.OrderNoteProductDto;
import com.gcom.operacional.entity.OrderNote;
import com.gcom.operacional.entity.OrderNoteProduct;
import com.gcom.operacional.entity.Product;
import com.gcom.operacional.repository.IOrderNoteProductRepository;
import com.gcom.operacional.repository.IProductRepository;
import com.gcom.operacional.token.UserPrincipal;

import lombok.extern.slf4j.Slf4j;

/**
 * OrderNoteServiceImpl clase que implementa la interfaz de servicio
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Service
@Slf4j
public class OrderNoteProductServiceImpl implements OrderNoteProductService {

	@Autowired
	private IOrderNoteProductRepository orderNoteProductRepository;
	
	@Autowired
	private IProductRepository productRepository;
	

	@Override
	public Boolean save(OrderNoteDto orderNote, OrderNote model, UserPrincipal token) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));

		List<OrderNoteProduct> orderNoteProductList = new ArrayList<>();
		orderNoteProductRepository.deleteAll(orderNoteProductRepository.findOrderNoteProductByIdOrderNote(orderNote.getId()));
		
		for (OrderNoteProductDto item : orderNote.getOrderNoteProductsList()) {
			Optional<Product> optProduct = productRepository.findById(item.getProductId());
			OrderNoteProduct temp = new OrderNoteProduct();
			temp.setAmount(item.getAmount());
			temp.setId(null);
			temp.setProduct(optProduct.get());
			temp.setOrderNote(model);
			temp.setSalePrice(item.getSalePrice());
			orderNoteProductList.add(temp);
		}
		orderNoteProductRepository.saveAll(orderNoteProductList);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}
	
}
