package com.gcom.operacional.service;

import java.util.List;

import com.gcom.operacional.dto.BillOfBuyProductDto;
import com.gcom.operacional.dto.InventoryTweaksProductDto;
import com.gcom.operacional.dto.OrderNoteProductDto;
import com.gcom.operacional.entity.BillOfBuyProduct;
import com.gcom.operacional.entity.OrderNoteProduct;
import com.gcom.operacional.entity.Product;


/**
 * UsersService servicio de usuario
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
public interface StockService {

	
	/**
	 * Save Bill of Buy Stock
	 * 
	 * @param List<BillOfBuyProductDto> @See {@link BillOfBuyProductDto}
	 * @return none
	 */
	public void saveBill(List<BillOfBuyProduct> list, Boolean isReverse) throws Exception;
	
	/**
	 * Save Order Note Stock
	 * 
	 * @param List<OrderNoteProductDto> @See {@link OrderNoteProductDto}
	 * @return none
	 */
	public void saveOrderNote(List<OrderNoteProduct> list, Boolean isReverse) throws Exception;
	
	/**
	 * Save Inventory Tweaks Stock
	 * 
	 * @param List<InventoryTweaksProductDto> @See {@link InventoryTweaksProductDto}
	 * @return none
	 */
	public void saveInventoryTweaks(List<InventoryTweaksProductDto> list, Boolean isReverse) throws Exception;
	
	/**
	 * Save Product
	 * 
	 * @param Long idProduct 
	 * @param Integer amount
	 * @return none
	 */
	public Product saveProduct(Long idProduct, Integer amount, Integer operation) throws Exception;
	
}
