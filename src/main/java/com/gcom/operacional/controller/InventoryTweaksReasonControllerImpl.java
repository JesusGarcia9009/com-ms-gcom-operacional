package com.gcom.operacional.controller;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;
import static com.gcom.operacional.utils.ConstantUtil.MSG_INVENTORY_REASON_DUPL;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcom.operacional.dto.InventoryTweaksReasonDto;
import com.gcom.operacional.service.InventoryTweaksReasonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${gcom.base-uri}/inventory-reason")
public class InventoryTweaksReasonControllerImpl implements InventoryTweaksReasonController {
	
	/**
	 * Global variables
	 */
	private final InventoryTweaksReasonService inventoryTweaksReasonService;
	
	/**
     * Class constructor with @autowire annotation
     * 
     * @param InventoryTweaksReasonsService @see {@link InventoryTweaksReasonsService}
     */
    public InventoryTweaksReasonControllerImpl(InventoryTweaksReasonService inventoryTweaksReasonService) {
        this.inventoryTweaksReasonService = inventoryTweaksReasonService;
    }

	
	@Override
	@GetMapping("/list")
	public ResponseEntity<List<InventoryTweaksReasonDto>> getInventoryTweaksReasonList() throws IOException {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<InventoryTweaksReasonDto> response = inventoryTweaksReasonService.findAllInventoryTweaksReason();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, response == null || response.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}


	@Override
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody InventoryTweaksReasonDto request) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		boolean response = false;

		try {
			response = inventoryTweaksReasonService.save(request);
		} catch (Exception e) {
			if (e.getMessage() == MSG_INVENTORY_REASON_DUPL) {
				return new ResponseEntity<String>(MSG_INVENTORY_REASON_DUPL, HttpStatus.BAD_REQUEST);
			} else {
				throw new Exception(e.getMessage());
			}
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@Override
	@PostMapping("/delete")
	public ResponseEntity<Boolean> delete(@RequestBody InventoryTweaksReasonDto request) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		boolean response = false;

		try {
			response = inventoryTweaksReasonService.delete(request);
		} catch (Exception e) {
				throw new Exception(e.getMessage());
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
