package com.gcom.operacional.service;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcom.operacional.dto.GenericAddressDto;
import com.gcom.operacional.repository.ICountryRepository;
import com.gcom.operacional.repository.IProvinceOrStateRepository;
import com.gcom.operacional.repository.IRegionOrCityRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * AddressServiceImpl clase que implementa la interfaz de servicio
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

	@Autowired
	private IRegionOrCityRepository regionOrCityRepository;
	
	@Autowired
	private IProvinceOrStateRepository provinceOrStateRepository;
	
	@Autowired
	private ICountryRepository countryRepository;

	@Override
	public List<GenericAddressDto> getProvinceOrStateList(Long regionOrCityId) {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<GenericAddressDto> result = null;
		
		if(regionOrCityId == -1l)
			result = provinceOrStateRepository.findAllProvinceOrState(null);
		else
			result = provinceOrStateRepository.findAllProvinceOrState(regionOrCityId);
		
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	@Override
	public List<GenericAddressDto> getRegionOrCityList(Long countryId) {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<GenericAddressDto> result = null;
		
		if(countryId == -1l)
			result = regionOrCityRepository.findAllRegionOrCity(null);
		else
			result = regionOrCityRepository.findAllRegionOrCity(countryId);
		
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	@Override
	public List<GenericAddressDto> getCountryList() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<GenericAddressDto> result = countryRepository.findAllCountry();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

}
