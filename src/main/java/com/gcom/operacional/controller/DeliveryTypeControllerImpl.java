package com.gcom.operacional.controller;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;
import static com.gcom.operacional.utils.ConstantUtil.MSG_DELIVERY_TYPE_DUPL;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcom.operacional.dto.DeliveryTypeDto;
import com.gcom.operacional.service.DeliveryTypeService;
import com.gcom.operacional.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${gcom.base-uri}/delivery")
public class DeliveryTypeControllerImpl implements DeliveryTypeController {
	
	/**
	 * Global variables
	 */
	private final DeliveryTypeService deliveryTypeService;
	
	/**
     * Class constructor with @autowire annotation
     * 
     * @param UsersService @see {@link UsersService}
     */
    public DeliveryTypeControllerImpl(DeliveryTypeService deliveryTypeService) {
        this.deliveryTypeService = deliveryTypeService;
    }

	@Override
	@GetMapping("/list")
	public ResponseEntity<List<DeliveryTypeDto>> getDeliveryTypeList() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<DeliveryTypeDto> response = deliveryTypeService.getDeliveryTypeList();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, response == null || response.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
	
	@Override
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody DeliveryTypeDto request) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		boolean response = false;

		try {
			response = deliveryTypeService.save(request);
		} catch (Exception e) {
			if (e.getMessage() == MSG_DELIVERY_TYPE_DUPL) {
				return new ResponseEntity<String>(MSG_DELIVERY_TYPE_DUPL, HttpStatus.BAD_REQUEST);
			} else {
				throw new Exception(e.getMessage());
			}
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	@PostMapping("/delete")
	public ResponseEntity<Boolean> delete(@RequestBody DeliveryTypeDto request) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		boolean response = false;

		try {
			response = deliveryTypeService.delete(request);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
