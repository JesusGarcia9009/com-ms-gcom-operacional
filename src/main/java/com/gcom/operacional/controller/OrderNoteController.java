package com.gcom.operacional.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.gcom.operacional.dto.OrderNoteBIllDto;
import com.gcom.operacional.dto.OrderNoteDto;
import com.gcom.operacional.dto.OrderNoteReverseDto;
import com.gcom.operacional.dto.QuotationAutoCompleteDto;
import com.gcom.operacional.token.UserPrincipal;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * OrderNoteController
 * 
 * @author Jesus Garcia
 */
@Api(value = "OrderNoteController")
public interface OrderNoteController {
	
	/**
	 * Method to list the quotation ids
	 * 
	 * @param long code
	 * @return result @See {@link QuotationAutoCompleteDto}
	 */
	@ApiOperation(value = "Get quotation list", notes = "Retorna los datos referente a las cotizaciones para hacer un auto complete")
	public ResponseEntity<List<QuotationAutoCompleteDto>> findByCode(String code)throws Exception;

	/**
	 * Method to list the orderNote of the products
	 * 
	 * @param dto OrderNoteDto.class 
	 * @return
	 */
	@ApiOperation(value = "Get orderNote list", notes = "Retorna los datos referente a las ordenes de pedido de la aplicacion")
	public ResponseEntity<List<OrderNoteDto>> findAll()throws Exception;
	
	/**
	 * getOrderNoteDocument Method.- Spring Boot
	 *
	 * @param @PathVariable Long id
	 * @param UserPrincipal token
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ResponseEntity<byte[]> see {@link Byte}
	 */
	@ApiOperation(value = "Get order note file", notes = "Obtiene el documento de orden de pedido de la aplicacion")
	public ResponseEntity<byte[]> getOrderNoteDocument(Long id, UserPrincipal token, HttpServletRequest request, HttpServletResponse response) throws Exception ;
	
	/**
	 * Method to save orderNote of the application
	 * 
	 * @param dto OrderNoteDto.class 
	 * @return boolean6
	 */
	@ApiOperation(value = "Save orderNote", notes = "Inserta o actualiza las ordenes de pedido de la aplicacion")
	public ResponseEntity<?> save(OrderNoteDto request, UserPrincipal token)throws Exception;
	
	/**
	 * Method to save orderNoteReverse of the application
	 * 
	 * @param dto OrderNoteReverseDto.class 
	 * @return boolean
	 */
	@ApiOperation(value = "Save orderNoteReverse", notes = "Inserta reversa de nota de pedido")
	public ResponseEntity<?> reverse(OrderNoteReverseDto request, UserPrincipal token)throws Exception;
	
	/**
	 * Method to bill order note of the application
	 * 
	 * @param request @see {@link OrderNoteBIllDto}
	 * @return any
	 */
	@ApiOperation(value = "Bill order note", notes = "Ingresa datos de la facturacion de la orden de pedido y cambia el estado a facturada")
	public ResponseEntity<?> bill(OrderNoteBIllDto request, UserPrincipal token)throws Exception;
	
	
	/**
	 * Method to delete orderNote of the application
	 * 
	 * @param dto OrderNoteDto.class 
	 * @return boolean
	 */
	@ApiOperation(value = "Delete orderNote", notes = "Elimina las orden de pedidos de la aplicacion")
	public ResponseEntity<Boolean> delete(@RequestBody OrderNoteDto request) throws Exception;
	
	
	
}
