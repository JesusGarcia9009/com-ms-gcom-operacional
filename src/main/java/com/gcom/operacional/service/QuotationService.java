package com.gcom.operacional.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcom.operacional.dto.QuotationDto;
import com.gcom.operacional.token.UserPrincipal;


/**
 * QuotationService servicio de usuario
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
public interface QuotationService {

	
	/**
	 * get quotation list
	 * 
	 * @param none
	 * @return list @see QuotationDto
	 */
	public List<QuotationDto> findAllQuotation();
	
	
	/**
	 * get quotation list
	 * 
	 * @param Long id
	 * @return dto @see QuotationDto
	 */
	public QuotationDto findById(Long id) throws Exception;
	
	/**
	 * save quotation 
	 * 
	 * @param quotation {@link QuotationDto}
	 * @return Boolean
	 */
	public Boolean save(QuotationDto quotation, UserPrincipal token) throws Exception; 	
	
	/**
	 * delete quotation 
	 * 
	 * @param quotation {@link QuotationDto}
	 * @return Boolean
	 */
	public boolean delete(QuotationDto dto) throws Exception;
	
	
	/**
	 * get Quotation Document
	 * 
	 * @param id {@link Long}
	 * @return byte[]
	 */
	public byte[] getQuotationDocument(Long id, UserPrincipal token, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
