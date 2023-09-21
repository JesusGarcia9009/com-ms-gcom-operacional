package com.gcom.operacional.controller;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;
import static com.gcom.operacional.utils.ConstantUtil.MSG_QUOTATION_DELIVERY_DUPL;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcom.operacional.dto.QuotationDeliveryDto;
import com.gcom.operacional.service.QuotationDeliveryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${gcom.base-uri}/quotation-delivery")
public class QuotationDeliveryControllerImpl implements QuotationDeliveryController {
	
	/**
	 * Global variables
	 */
	private final QuotationDeliveryService sourceService;
	
	/**
     * Class constructor with @autowire annotation
     * 
     * @param QuotationDeliverysService @see {@link QuotationDeliverysService}
     */
    public QuotationDeliveryControllerImpl(QuotationDeliveryService sourceService) {
        this.sourceService = sourceService;
    }

	
	@Override
	@GetMapping("/list")
	public ResponseEntity<List<QuotationDeliveryDto>> getQuotationDeliveryList() throws IOException {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<QuotationDeliveryDto> response = sourceService.findAllQuotationDelivery();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, response == null || response.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}


	@Override
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody QuotationDeliveryDto request) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		boolean response = false;

		try {
			response = sourceService.save(request);
		} catch (Exception e) {
			if (e.getMessage() == MSG_QUOTATION_DELIVERY_DUPL) {
				return new ResponseEntity<String>(MSG_QUOTATION_DELIVERY_DUPL, HttpStatus.BAD_REQUEST);
			} else {
				throw new Exception(e.getMessage());
			}
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@Override
	@PostMapping("/delete")
	public ResponseEntity<Boolean> delete(@RequestBody QuotationDeliveryDto request) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		boolean response = false;

		try {
			response = sourceService.delete(request);
		} catch (Exception e) {
				throw new Exception(e.getMessage());
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
