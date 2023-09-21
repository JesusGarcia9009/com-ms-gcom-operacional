package com.gcom.operacional.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcom.operacional.dto.BillOfBuyDto;
import com.gcom.operacional.dto.BillOfBuyReverseDto;
import com.gcom.operacional.entity.BillOfBuy;
import com.gcom.operacional.token.UserPrincipal;


/**
 * BillService servicio de usuario
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
public interface BillService {
	
	
	/**
	 * get bill list
	 * 
	 * @param none
	 * @return list @see BillDto
	 */
	public List<BillOfBuyDto> findAll();
	
	/**
	 * save bill 
	 * 
	 * @param bill {@link BillOfBuyDto}
	 * @return Boolean
	 */
	public Boolean save(BillOfBuyDto bill, UserPrincipal token) throws Exception; 	
	
	/**
	 * delete bill 
	 * 
	 * @param bill {@link BillOfBuyDto}
	 * @return Boolean
	 */
	public boolean reverse(BillOfBuyReverseDto dto, UserPrincipal token) throws Exception;
	
	/**
	 * emit Bill of Buy
	 * 
	 * @param id {@link Long}
	 * @return byte[]
	 */
	public boolean emit(BillOfBuy model, UserPrincipal token) throws Exception;
	
	
	/**
	 * get Bill Document
	 * 
	 * @param id {@link Long}
	 * @return byte[]
	 */
	public byte[] getBillDocument(Long id, UserPrincipal token, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
