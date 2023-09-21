package com.gcom.operacional.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcom.operacional.dto.OrderNoteBIllDto;
import com.gcom.operacional.dto.OrderNoteDto;
import com.gcom.operacional.dto.OrderNoteReverseDto;
import com.gcom.operacional.dto.QuotationAutoCompleteDto;
import com.gcom.operacional.entity.OrderNote;
import com.gcom.operacional.token.UserPrincipal;


/**
 * OrderNoteService servicio de usuario
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
public interface OrderNoteService {
	
	
	/**
	 * Method to list the quotation ids
	 * 
	 * @param long code
	 * @return result @See {@link QuotationAutoCompleteDto}
	 */
	public List<QuotationAutoCompleteDto> findByCode(String code);

	
	/**
	 * get orderNote list
	 * 
	 * @param none
	 * @return list @see OrderNoteDto
	 */
	public List<OrderNoteDto> findAll();
	
	/**
	 * save orderNote 
	 * 
	 * @param orderNote {@link OrderNoteDto}
	 * @return Boolean
	 */
	public Boolean save(OrderNoteDto orderNote, UserPrincipal token) throws Exception; 	
	
	/**
	 * save reverse of the order Note 
	 * 
	 * @param orderNoteReverse {@link OrderNoteReverseDto}
	 * @return Boolean
	 */
	public Boolean reverse(OrderNoteReverseDto req, UserPrincipal token) throws Exception; 	
	
	/**
	 * bill order Note (facturar) 
	 * 
	 * @param request {@link OrderNoteBIllDto}
	 * @return Boolean
	 */
	public Boolean bill(OrderNoteBIllDto req, UserPrincipal token) throws Exception;
	
	/**
	 * delete orderNote 
	 * 
	 * @param orderNote {@link OrderNoteDto}
	 * @return Boolean
	 */
	public boolean delete(OrderNoteDto dto) throws Exception;
	
	/**
	 * emit Order Note
	 * 
	 * @param id {@link Long}
	 * @return byte[]
	 */
	public boolean emit(OrderNote model, UserPrincipal token) throws Exception;
	
	
	/**
	 * get OrderNote Document
	 * 
	 * @param id {@link Long}
	 * @return byte[]
	 */
	public byte[] getOrderNoteDocument(Long id, UserPrincipal token, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	
}
