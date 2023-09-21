package com.gcom.operacional.service;

import com.gcom.operacional.dto.OrderNoteDto;
import com.gcom.operacional.entity.OrderNote;
import com.gcom.operacional.token.UserPrincipal;


/**
 * OrderNoteProductService servicio de productos de una orden de pedido
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
public interface OrderNoteProductService {
	
	
	/**
	 * save orderNoteProduct 
	 * 
	 * @param orderNote {@link OrderNoteDto}
	 * @return Boolean
	 */
	public Boolean save(OrderNoteDto orderNote, OrderNote model, UserPrincipal token) throws Exception; 	
	
	
	
	
	
}
