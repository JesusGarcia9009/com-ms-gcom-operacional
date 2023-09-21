package com.gcom.operacional.service;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcom.operacional.dto.DashboardDataDto;
import com.gcom.operacional.dto.DashboardOrderNoteDataDto;
import com.gcom.operacional.dto.DashboardProductsDataDto;
import com.gcom.operacional.dto.DashboardQuotationDataDto;
import com.gcom.operacional.repository.IDashboardRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * DashboardServiceImpl clase que implementa la interfaz de servicio
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Service
@Slf4j
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private IDashboardRepository dashboardRepository;
	
	
	@Override
	public DashboardDataDto getDashboardWidgetData() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		DashboardDataDto result = new DashboardDataDto();
		Object object = dashboardRepository.getDashboardWidgetData();
		
		Object[] arraysObjects = (Object[]) object;
		result.setProductsGreater(((BigInteger) arraysObjects[0]));
		result.setProductsLess((BigInteger) arraysObjects[1]);
//		result.setOrderNoteNumber((BigInteger) arraysObjects[2]);
		result.setBillOfBuyNumber((BigInteger) arraysObjects[2]);
//		result.setQuotationNumber((BigInteger) arraysObjects[4]);
		
		Object objectQuotation = dashboardRepository.getDashboardWidgetQuotationData();
		Object[] arraysQuotationObjects = (Object[]) objectQuotation;
		result.setQuotationNumber((BigInteger) arraysQuotationObjects[0]);
		result.setQuotationAmount(arraysQuotationObjects[1] != null ? (BigDecimal) arraysQuotationObjects[1]: BigDecimal.ZERO);
		
		Object objectOrderNote = dashboardRepository.getDashboardWidgetOrderNoteData();
		Object[] arraysOrderNoteObjects = (Object[]) objectOrderNote;
		result.setOrderNoteNumber((BigInteger) arraysOrderNoteObjects[0]);
		result.setOrderNoteAmount(arraysOrderNoteObjects[1] != null ? (BigDecimal) arraysOrderNoteObjects[1]: BigDecimal.ZERO);
		
		List<Object> listado = dashboardRepository.getDashboardQuotationDataCurrentWeek();
		List<DashboardQuotationDataDto> resultQuotationlist = new ArrayList<>();
		for (Object item : listado) {
			DashboardQuotationDataDto aux = new DashboardQuotationDataDto();
			Object[] arrays = (Object[]) item;
			aux.setWeekDay(((String) arrays[0]));
			aux.setNumberQuotation((BigInteger) arrays[1]);
			resultQuotationlist.add(aux);
		}
		result.setQuotationList(resultQuotationlist);
		
		List<Object> listado1 = dashboardRepository.getDashboardOrderNoteDataCurrentWeek();
		List<DashboardOrderNoteDataDto> resultOrderNoteList = new ArrayList<>();
		for (Object item : listado1) {
			DashboardOrderNoteDataDto aux = new DashboardOrderNoteDataDto();
			Object[] arrays = (Object[]) item;
			aux.setWeekDay(((String) arrays[0]));
			aux.setNumberOrderNote((BigInteger) arrays[1]);
			resultOrderNoteList.add(aux);
		}
		result.setOrderNoteList(resultOrderNoteList);
		
		List<Object> moreSales = dashboardRepository.getDashboardBestSellingProductsMonth();
		List<DashboardProductsDataDto> moreSellersProduct = new ArrayList<>();
		for (Object item : moreSales) {
			DashboardProductsDataDto aux = new DashboardProductsDataDto();
			Object[] arrays = (Object[]) item;
			aux.setId(((Integer) arrays[0]).longValue());
			aux.setProductDescription(((String) arrays[1]));
			aux.setProductGis(((String) arrays[2]));
			aux.setAmount(((BigDecimal) arrays[3]).longValue());
			moreSellersProduct.add(aux);
		}
		
		
		List<Object> lessSales = dashboardRepository.getDashboardLesserSellingProductsMonth();
		List<DashboardProductsDataDto> lessSellersProduct = new ArrayList<>();
		
		for (Object item : lessSales) {
			DashboardProductsDataDto aux = new DashboardProductsDataDto();
			Object[] arrays = (Object[]) item;
			aux.setId(((Integer) arrays[0]).longValue());
			aux.setProductDescription(((String) arrays[1]));
			aux.setProductGis(((String) arrays[2]));
			aux.setAmount(((BigDecimal) arrays[3]).longValue());
			lessSellersProduct.add(aux);
		}
		
		result.setMoreSellersProduct(moreSellersProduct);
		result.setLessSellersProduct(lessSellersProduct);
		
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

}
