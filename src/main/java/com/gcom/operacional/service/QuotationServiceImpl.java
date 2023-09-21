package com.gcom.operacional.service;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import com.gcom.operacional.dto.QuotationDto;
import com.gcom.operacional.dto.QuotationProductDto;
import com.gcom.operacional.entity.Client;
import com.gcom.operacional.entity.Product;
import com.gcom.operacional.entity.Quotation;
import com.gcom.operacional.entity.QuotationProduct;
import com.gcom.operacional.entity.QuotationState;
import com.gcom.operacional.repository.IClientRepository;
import com.gcom.operacional.repository.IProductRepository;
import com.gcom.operacional.repository.IQuotationProductRepository;
import com.gcom.operacional.repository.IQuotationRepository;
import com.gcom.operacional.repository.IQuotationStateRepository;
import com.gcom.operacional.token.UserPrincipal;
import com.gcom.operacional.utils.Utils;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

import lombok.extern.slf4j.Slf4j;

/**
 * QuotationServiceImpl clase que implementa la interfaz de servicio
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Service
@Slf4j
public class QuotationServiceImpl implements QuotationService {

	@Autowired
	private IQuotationRepository quotationRepository;
	
	@Autowired
	private IClientRepository clientRepository;
	
	@Autowired
	private IQuotationStateRepository quotationStateRepository;
	
	@Autowired
	private IQuotationProductRepository quotationProductRepository;
	
	@Autowired
	private IProductRepository productRepository;
	
	@Value("${gcom.iva}")
	private BigDecimal iva;
	
	@Autowired
	private Utils utils;
	
	@Autowired
    private ServletContext servletContext;
	
	@Autowired
	private TemplateEngine templateEngine;

	@Override
	public List<QuotationDto> findAllQuotation() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<QuotationDto> result = quotationRepository.findAllQuotations();
		for (QuotationDto quotationDto : result) {
			BigDecimal total = BigDecimal.ZERO;
			
			List<QuotationProductDto> list = quotationProductRepository.findQuotationProductsByIdQuotation(quotationDto.getId());
			int subTotal = 0;
			int descuento = 0;
			int neto = 0;
			int iva = 0;
			for (QuotationProductDto item : list) {
				subTotal += item.getAmount().intValue() * item.getSalePrice().intValue(); 
			}
			descuento = (subTotal * quotationDto.getDiscount().intValue()) / 100;
			neto = subTotal - descuento;
			iva = Math.round((neto * this.iva.intValue()) / 100);
			total = new BigDecimal(neto + iva);
			quotationDto.setTotal(total);
			quotationDto.setQuotationProductList(list);
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}
	
	@Override
	public QuotationDto findById(Long id) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		QuotationDto result = quotationRepository.findByIdQ(id);
		result.setQuotationProductList(quotationProductRepository.findQuotationProductsByIdQuotation(id));
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	@Override
	public Boolean save(QuotationDto quotation, UserPrincipal token) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));

		Quotation quotationModel = new Quotation();
		Date now = new Date();
		Client client = utils.validateOpt(clientRepository.findById(quotation.getClientId()));
		QuotationState state = utils.validateOpt(quotationStateRepository.findByNameState("INGRESADA"));
		
		if (Objects.nonNull(quotation.getId()))
			quotationModel.setId(quotation.getId());
		else
			quotationModel.setGenerationDate(now);
		
	    quotationModel.setId(quotation.getId());
		quotationModel.setUpdateName(token.getFullName());
		quotationModel.setUpdateMail(token.getMail());
		quotationModel.setUpdateDate(now);
		quotationModel.setGenerationDate(now);
		quotationModel.setDiscount(quotation.getDiscount());
		quotationModel.setIva(this.iva);
		quotationModel.setAdditionalInformation( quotation.getAdditionalInformation());
		quotationModel.setClient(client);
		quotationModel.setQuotationState(state);
		quotationModel.setAttention(quotation.getAttention());

		quotationRepository.save(quotationModel);
		List<QuotationProduct> quotationProductList = new ArrayList<>();
		
		quotationProductRepository.deleteAll(quotationProductRepository.findQuotProdByIdQuotation(quotation.getId()));
		
		for (QuotationProductDto item : quotation.getQuotationProductList()) {
			Optional<Product> optProduct = productRepository.findById(item.getProductId());
			QuotationProduct temp = new QuotationProduct();
			temp.setAmount(item.getAmount());
			temp.setId(item.getId());
			temp.setProduct(optProduct.get());
			temp.setQuotation(quotationModel);
			temp.setSalePrice(item.getSalePrice());
			temp.setDelivery(item.getDelivery());
			quotationProductList.add(temp);
		}
		quotationProductRepository.saveAll(quotationProductList);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

	@Override
	public boolean delete(QuotationDto dto) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		quotationProductRepository.deleteAll(quotationProductRepository.findQuotProdByIdQuotation(dto.getId()));
		quotationRepository.deleteById(dto.getId());
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

	@Override
	public byte[] getQuotationDocument(Long id, UserPrincipal token, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		Quotation model = utils.validateOpt(quotationRepository.findById(id));
		Client modelClient = utils.validateOpt(clientRepository.findById(model.getClient().getId()));
		List<QuotationProductDto> list = quotationProductRepository.findQuotationProductsByIdQuotationWithRowsColls(model.getId());
		
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
        context.setVariable("quotation", model);
        context.setVariable("now", dateString);
        context.setVariable("quotationProductList", list);
        context.setVariable("client", modelClient);
        context.setVariable("attentionMail", token.getMail());
        context.setVariable("descuento_100", model.getDiscount().intValue());
        
        String orderHtml = templateEngine.process("quotation.html", context);

        ByteArrayOutputStream target = new ByteArrayOutputStream();
        ConverterProperties converterProperties = new ConverterProperties();
        HtmlConverter.convertToPdf(orderHtml, target, converterProperties);

        byte[] bytes = target.toByteArray();
        QuotationState stateCheck = utils.validateOpt(quotationStateRepository.findById(model.getQuotationState().getId()));
        if(stateCheck.getNameState().equalsIgnoreCase("INGRESADA")) {
        	QuotationState state = utils.validateOpt(quotationStateRepository.findByNameState("EMITIDA"));
            model.setQuotationState(state);
            quotationRepository.save(model);
        }
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return bytes;
	}

	

}
