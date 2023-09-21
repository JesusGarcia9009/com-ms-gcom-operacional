package com.gcom.operacional.service;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.gcom.operacional.dto.BillOfBuyDto;
import com.gcom.operacional.dto.BillOfBuyProductDto;
import com.gcom.operacional.dto.BillOfBuyReverseDto;
import com.gcom.operacional.dto.ProviderDto;
import com.gcom.operacional.entity.BillOfBuy;
import com.gcom.operacional.entity.BillOfBuyProduct;
import com.gcom.operacional.entity.BillOfBuyReverse;
import com.gcom.operacional.entity.BillOfBuyState;
import com.gcom.operacional.entity.Product;
import com.gcom.operacional.entity.Provider;
import com.gcom.operacional.repository.IBillOfBuyProductRepository;
import com.gcom.operacional.repository.IBillOfBuyRepository;
import com.gcom.operacional.repository.IBillOfBuyReverseRepository;
import com.gcom.operacional.repository.IBillOfBuyStateRepository;
import com.gcom.operacional.repository.IProductRepository;
import com.gcom.operacional.repository.IProviderRepository;
import com.gcom.operacional.token.UserPrincipal;
import com.gcom.operacional.utils.NumberToWords;
import com.gcom.operacional.utils.Utils;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

import lombok.extern.slf4j.Slf4j;

/**
 * BillServiceImpl clase que implementa la interfaz de servicio
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Service
@Slf4j
public class BillServiceImpl implements BillService {
	
	@Autowired
	private OperationLogService operationLogService;

	@Autowired
	private IBillOfBuyRepository billOfBuyRepository;
	
	@Autowired
	private IProviderRepository providerRepository;
	
	@Autowired
	private IBillOfBuyStateRepository billOfBuyStateRepository;
	
	@Autowired
	private IBillOfBuyReverseRepository billOfBuyReverseRepository;
	
	@Autowired
	private IBillOfBuyProductRepository billOfBuyProductRepository;
	
	@Autowired
	private IProductRepository productRepository;
	
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
	public List<BillOfBuyDto> findAll() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<BillOfBuyDto> result = billOfBuyRepository.findAllBillOfBuys();
		for (BillOfBuyDto billDto : result) {
			billDto.setBillOfBuyProductsList(billOfBuyProductRepository.findBillOfBuyProductsByIdBillOfBuy(billDto.getId()));
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	@Override
	public Boolean save(BillOfBuyDto bill, UserPrincipal token) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));

		BillOfBuy billModel = new BillOfBuy();
		Date now = new Date();
		Provider provider = utils.validateOpt(providerRepository.findById(bill.getProviderId()));
		BillOfBuyState state = utils.validateOpt(billOfBuyStateRepository.findByNameState("INGRESADA"));

		if (Objects.nonNull(bill.getId()))
			billModel.setId(bill.getId());
		else
			billModel.setGenerationDate(now);
		
	    billModel.setId(bill.getId());
		billModel.setUsername(token.getFullName());
		billModel.setUpdateDate(now);
		billModel.setGenerationDate(now);
		billModel.setDiscount(bill.getDiscount());
		billModel.setIva(this.iva);
		billModel.setAdditionalInformation( bill.getAdditionalInformation());
		billModel.setProvider(provider);
		billModel.setBillOfBuyState(state);
		billModel = billOfBuyRepository.save(billModel);
		List<BillOfBuyProduct> billProductList = new ArrayList<>();
		
		billOfBuyProductRepository.deleteAll(billOfBuyProductRepository.findBillProductsByIdBillOfBuy(bill.getId()));
		
		for (BillOfBuyProductDto item : bill.getBillOfBuyProductsList()) {
			Optional<Product> optProduct = productRepository.findById(item.getProductId());
			BillOfBuyProduct temp = new BillOfBuyProduct();
			temp.setAmount(item.getAmount());
			temp.setId(item.getId());
			temp.setProduct(optProduct.get());
			temp.setBillOfBuy(billModel);
			temp.setSalePrice(item.getSalePrice());
			billProductList.add(temp);
		}
		billOfBuyProductRepository.saveAll(billProductList);
		
		operationLogService.saveOperationBillOfBuy(billModel.getId(), Objects.nonNull(bill.getId()) ? "ACTUALIZADA" : "INGRESADA" , token);
		
		
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}
	
	@Override
	public boolean reverse(BillOfBuyReverseDto dto, UserPrincipal token) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		Date now = new Date();
		BillOfBuyState state = utils.validateOpt(billOfBuyStateRepository.findByNameState("REVERSADA"));
		BillOfBuy model = utils.validateOpt(billOfBuyRepository.findById(dto.getIdBillOfBuy()));
		
		model.setBillOfBuyState(state);
		model.setUsername(token.getFullName());
		model.setUpdateDate(now);
		billOfBuyRepository.save(model);
		stockService.saveBill(new ArrayList<>(model.getBillOfBuyProducts()), Boolean.TRUE);
		
		BillOfBuyReverse billOfBuyReverse = new BillOfBuyReverse();

		billOfBuyReverse.setId(dto.getId());
		billOfBuyReverse.setBillOfBuy(model);
		billOfBuyReverse.setUpdateDate(now);
		billOfBuyReverse.setUpdateName(token.getFullName());
		billOfBuyReverse.setAdditionalInformation(dto.getAdditionalInformation());
		
		billOfBuyReverseRepository.save(billOfBuyReverse);
		
		operationLogService.saveOperationBillOfBuy(dto.getIdBillOfBuy(), "REVERSADA" , token);
		
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}
	
	@Override
	public boolean emit(BillOfBuy model, UserPrincipal token) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		BillOfBuyState state = utils.validateOpt(billOfBuyStateRepository.findByNameState("EMITIDA"));
		
		if(Objects.isNull(state)) return Boolean.FALSE;
		
		model.setBillOfBuyState(state);
		model.setUsername(token.getFullName());
		model.setUpdateDate(new Date());
		billOfBuyRepository.save(model);
		stockService.saveBill(new ArrayList<>(model.getBillOfBuyProducts()), Boolean.FALSE);
		operationLogService.saveOperationBillOfBuy(model.getId(), "EMITIDA", token);
		
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

	@Override
	public byte[] getBillDocument(Long id, UserPrincipal token, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		Optional<BillOfBuy> model = billOfBuyRepository.findById(id);
		ProviderDto provider = providerRepository.findProviderById(model.get().getProvider().getId());
		List<BillOfBuyProductDto> list = billOfBuyProductRepository.findBillOfBuyProductByIdBillOfBuyWithRowsColls(model.get().getId());
		
		
		int subTotal = 0;
		int descuento = 0;
		int neto = 0;
		int iva = 0;
		int total = 0;
		for (int i = 0; i < list.size(); i++) {
			subTotal += list.get(i).getAmount().intValue() * list.get(i).getSalePrice().intValue(); 
		}
		descuento = (subTotal * (Objects.nonNull(model.get().getDiscount()) ? model.get().getDiscount().intValue() : 0) ) / 100;
		neto = subTotal - descuento;
		iva = (neto * this.iva.intValue()) / 100;
		total = neto + iva;
		
		String MES[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	    LocalDate date = model.get().getGenerationDate().toInstant().atZone(ZoneId.of("UTC")).toLocalDate();
	    String dateString = (date.getDayOfMonth() + " de "+MES[date.getMonthValue()-1]+" de " + date.getYear());
		

		
		Map<String, Object> data = new HashMap<>();
		data.put("subTotal", subTotal);
		data.put("descuento", descuento);
		data.put("neto", neto);
		data.put("iva", iva);
		data.put("total", total);
		data.put("now", dateString);
		data.put("bill", model.get());
		data.put("provider", provider);
		data.put("amount", NumberToWords.ConvertidorPesos.convertir(total));
		data.put("billProductList", list);

        WebContext context = new WebContext(request, response, servletContext);
        context.setVariable("subTotal", subTotal);
        context.setVariable("descuento", descuento);
        context.setVariable("neto", neto);
        context.setVariable("iva", iva);
        context.setVariable("total", total);
        
        context.setVariable("bill", model.get());
        context.setVariable("now", dateString);
        context.setVariable("billProductList", list);
        context.setVariable("provider", provider);
        context.setVariable("amount", NumberToWords.ConvertidorPesos.convertir(total));
        String orderHtml = templateEngine.process("billOfBuy.html", context);

        ByteArrayOutputStream target = new ByteArrayOutputStream();
        ConverterProperties converterProperties = new ConverterProperties();
        HtmlConverter.convertToPdf(orderHtml, target, converterProperties);

        /* extract output as bytes */
        byte[] bytes = target.toByteArray();
        BillOfBuyState state = utils.validateOpt(billOfBuyStateRepository.findById(model.get().getBillOfBuyState().getId()));
        if(state.getNameState().equalsIgnoreCase("INGRESADA"))
        	this.emit(model.get(), token);
        
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return bytes;
	}

}
