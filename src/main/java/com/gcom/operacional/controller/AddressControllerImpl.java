package com.gcom.operacional.controller;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcom.operacional.dto.GenericAddressDto;
import com.gcom.operacional.service.AddressService;
import com.gcom.operacional.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${gcom.base-uri}/address")
public class AddressControllerImpl implements AddressController {
	
	/**
	 * Global variables
	 */
	private final AddressService addressService;
	
	/**
     * Class constructor with @autowire annotation
     * 
     * @param UsersService @see {@link UsersService}
     */
    public AddressControllerImpl(AddressService addressService) {
        this.addressService = addressService;
    }

	
	@Override
	@GetMapping("/list-province-state/{regionOrCityId}")
	public ResponseEntity<List<GenericAddressDto>> getProvinceOrStateList(@PathVariable Long regionOrCityId) {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<GenericAddressDto> response = addressService.getProvinceOrStateList(regionOrCityId);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, response == null || response.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}


	@Override
	@GetMapping("/list-region-city/{countryId}")
	public ResponseEntity<List<GenericAddressDto>> getRegionOrCityList(@PathVariable Long countryId) {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<GenericAddressDto> response = addressService.getRegionOrCityList(countryId);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, response == null || response.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}


	@Override
	@GetMapping("/list-country")
	public ResponseEntity<List<GenericAddressDto>> getCountryList() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<GenericAddressDto> response = addressService.getCountryList();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, response == null || response.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
	
}
