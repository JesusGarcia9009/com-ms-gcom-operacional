package com.gcom.operacional.controller;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcom.operacional.dto.OperationLogDto;
import com.gcom.operacional.dto.OperationLogRequestDto;
import com.gcom.operacional.dto.OperationTypeDto;
import com.gcom.operacional.service.OperationLogService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${gcom.base-uri}/record")
public class OperationLogControllerImpl implements OperationLogController {
	
	/**
	 * Global variables
	 */
	private final OperationLogService operationLogService;
	
	/**
     * Class constructor with @autowire annotation
     * 
     * @param BillsService @see {@link OperationLogService}
     */
    public OperationLogControllerImpl(OperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }
    
	@Override
	@PostMapping("/find-by-date")
	public ResponseEntity<List<OperationLogDto>> getRecordByDates(@RequestBody OperationLogRequestDto request) {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<OperationLogDto> response = operationLogService.findAllOperationLog(request);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, response == null || response.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}

	@Override
	@GetMapping("/list-users")
	public ResponseEntity<List<String>> getUserList() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<String> response = operationLogService.findAllUsers();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, response == null || response.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}

	@Override
	@GetMapping("/list-state")
	public ResponseEntity<List<String>> getStateList() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<String> response = operationLogService.findAllState();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, response == null || response.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
	
	@Override
	@GetMapping("/list-type")
	public ResponseEntity<List<OperationTypeDto>> getOperationTypeList() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<OperationTypeDto> response = operationLogService.findAllOperationType();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, response == null || response.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}

}
