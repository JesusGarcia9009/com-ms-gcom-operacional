package com.gcom.operacional.service;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import com.gcom.operacional.dto.ClientDto;
import com.gcom.operacional.dto.OrderNoteBIllDto;
import com.gcom.operacional.dto.OrderNoteDto;
import com.gcom.operacional.dto.OrderNoteProductDto;
import com.gcom.operacional.dto.OrderNoteReverseDto;
import com.gcom.operacional.dto.QuotationAutoCompleteDto;
import com.gcom.operacional.entity.Client;
import com.gcom.operacional.entity.OrderNote;
import com.gcom.operacional.entity.OrderNoteReverse;
import com.gcom.operacional.entity.OrderNoteState;
import com.gcom.operacional.repository.IClientRepository;
import com.gcom.operacional.repository.IOrderNoteProductRepository;
import com.gcom.operacional.repository.IOrderNoteRepository;
import com.gcom.operacional.repository.IOrderNoteReverseRepository;
import com.gcom.operacional.repository.IOrderNoteStateRepository;
import com.gcom.operacional.repository.IQuotationRepository;
import com.gcom.operacional.token.UserPrincipal;
import com.gcom.operacional.utils.NumberToWords;
import com.gcom.operacional.utils.Utils;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

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
public class OrderNoteServiceImpl implements OrderNoteService {

	@Autowired
	private IOrderNoteRepository orderNoteRepository;
	
	@Autowired
	private IClientRepository clientRepository;
	
	@Autowired
	private ClientServiceImpl clientServiceImpl;
	
	@Autowired
	private IOrderNoteStateRepository orderNoteStateRepository;
	
	@Autowired
	private IOrderNoteProductRepository orderNoteProductRepository;
	
	@Autowired
	private IQuotationRepository quotationRepository;
	
	@Autowired
	private IOrderNoteReverseRepository orderNoteReverseRepository;
	
	@Autowired
	private OrderNoteProductService orderNoteProductService;
	
	@Autowired
	private OperationLogService operationLogService;
	
	
	@Value("${gcom.iva}")
	private BigDecimal iva;
	
	@Autowired
	private Utils utils;
	
	@Autowired
	private StockService stockService;
	
	@Autowired
    private ServletContext servletContext;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Override
	public List<QuotationAutoCompleteDto> findByCode(String code) {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<QuotationAutoCompleteDto> result = quotationRepository.findQuotationsByCode(code);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	@Override
	public List<OrderNoteDto> findAll() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<OrderNoteDto> result = orderNoteRepository.findAllOrderNotes();
		for (OrderNoteDto orderNoteDto : result) {
			orderNoteDto.setOrderNoteProductsList(orderNoteProductRepository.findOrderNoteProductsByIdOrderNote(orderNoteDto.getId()));
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	@Override
	public Boolean save(OrderNoteDto orderNote, UserPrincipal token) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		Client client = utils.validateOpt(clientRepository.findById(orderNote.getClientId()));
		OrderNoteState state = utils.validateOpt(orderNoteStateRepository.findByNameState("INGRESADA"));
		
		if(Objects.isNull(client) || Objects.isNull(state)) return false;

		OrderNote model = utils.getOrderNote(orderNote, token, client, state); 
		
		model = orderNoteRepository.save(model);
		
		orderNoteProductService.save(orderNote, model, token);
		
		operationLogService.saveOperationOrderNote(model.getId(), Objects.nonNull(orderNote.getId()) ? "ACTUALIZADA" : "INGRESADA" , token);
		
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}
	
	@Override
	public boolean emit(OrderNote model, UserPrincipal token) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		OrderNoteState state = utils.validateOpt(orderNoteStateRepository.findByNameState("EMITIDA"));
		
		if(Objects.isNull(state)) return Boolean.FALSE;
		
		model.setOrderNoteState(state);
		model.setUpdateName(token.getFullName());
		model.setUpdateDate(new Date());
		orderNoteRepository.save(model);
		stockService.saveOrderNote(new ArrayList<>(model.getOrderNoteProducts()), Boolean.FALSE);
		operationLogService.saveOperationOrderNote(model.getId(), "EMITIDA", token);
		
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}
	
	@Override
	public Boolean reverse(OrderNoteReverseDto req, UserPrincipal token) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		Date now = new Date();
		OrderNoteState state = utils.validateOpt(orderNoteStateRepository.findByNameState("REVERSADA"));
		OrderNote orderNote = utils.validateOpt(orderNoteRepository.findById(req.getIdOrderNote()));
		
		orderNote.setOrderNoteState(state);
		orderNote.setUpdateName(token.getFullName());
		orderNote.setUpdateDate(now);
		orderNoteRepository.save(orderNote);
		stockService.saveOrderNote(new ArrayList<>(orderNote.getOrderNoteProducts()), Boolean.TRUE);
		
		OrderNoteReverse orderNoteReverse = new OrderNoteReverse();

		orderNoteReverse.setId(req.getId());
		orderNoteReverse.setDateOfCreditNote(req.getDateOfCreditNote());
		orderNoteReverse.setNumberOfCreditNote(req.getNumberOfCreditNote());
		orderNoteReverse.setOrderNote(orderNote);
		orderNoteReverse.setUpdateDate(now);
		orderNoteReverse.setUpdateName(token.getFullName());
		orderNoteReverse.setAdditionalInformation(req.getAdditionalInformation());
		
		orderNoteReverseRepository.save(orderNoteReverse);
		
		operationLogService.saveOperationOrderNote(req.getIdOrderNote(), "REVERSADA" , token);
		
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}
	
	@Override
	public Boolean bill(OrderNoteBIllDto dto, UserPrincipal token) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));

		OrderNote model = utils.validateOpt(orderNoteRepository.findById(dto.getId()));
		
		Date now = new Date();
		
		model.setNumberOfBill(dto.getNumberOfBill());
		model.setDateOfBill(dto.getDateOfBill());
		
		model.setUpdateDate(now);
		model.setUpdateName(token.getFullName());
		
		OrderNoteState state = utils.validateOpt(orderNoteStateRepository.findByNameState("FACTURADA"));
		model.setOrderNoteState(state);
			
		orderNoteRepository.save(model);
		
		operationLogService.saveOperationOrderNote(model.getId(), "FACTURADA" , token);
		
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}


	@Override
	public boolean delete(OrderNoteDto dto) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		orderNoteProductRepository.deleteAll(orderNoteProductRepository.findOrderNoteProductByIdOrderNote(dto.getId()));
		orderNoteRepository.deleteById(dto.getId());
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

	@Override
	public byte[] getOrderNoteDocument(Long id, UserPrincipal token, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		OrderNote model = utils.validateOpt(orderNoteRepository.findById(id));
		ClientDto client = clientServiceImpl.findClientById(model.getClient().getId());
		List<OrderNoteProductDto> list = orderNoteProductRepository.findOrderNoteProductsByIdOrderNoteWithRowsColls(model.getId());
		
		
		int subTotal = 0, descuento = 0, neto = 0, iva = 0, total = 0;
		for (int i = 0; i < list.size(); i++) {
			subTotal += list.get(i).getAmount().intValue() * list.get(i).getSalePrice().intValue(); 
		}
		descuento = (subTotal * model.getDiscount().intValue()) / 100;
		neto = subTotal - descuento;
		iva = Math.round((neto * this.iva.intValue()) / 100);
		total = neto + iva;
		
	    String dateString = utils.getStringDate(model.getGenerationDate());
		
        WebContext context = new WebContext(request, response, servletContext);
        context.setVariable("subTotal", subTotal);
        context.setVariable("descuento", descuento);
        context.setVariable("neto", neto);
        context.setVariable("iva", iva);
        context.setVariable("total", total);
        context.setVariable("orderNote", model);
        context.setVariable("now", dateString);
        context.setVariable("orderNoteProductList", list);
        context.setVariable("client", client);
        context.setVariable("amount", NumberToWords.ConvertidorPesos.convertir(total));
        context.setVariable("descuento_100", model.getDiscount().intValue());
        
        String orderHtml = templateEngine.process("orderNote.html", context);

        ByteArrayOutputStream target = new ByteArrayOutputStream();
        ConverterProperties converterProperties = new ConverterProperties();
        HtmlConverter.convertToPdf(orderHtml, target, converterProperties);

        /* extract output as bytes */
        byte[] bytes = target.toByteArray();
        OrderNoteState state = utils.validateOpt(orderNoteStateRepository.findById(model.getOrderNoteState().getId()));
        if(state.getNameState().equalsIgnoreCase("INGRESADA"))
        	this.emit(model, token);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return bytes;
	}

	
	

}
