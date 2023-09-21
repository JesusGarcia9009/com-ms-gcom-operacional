package com.gcom.operacional.controller;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;
import static com.gcom.operacional.utils.ConstantUtil.MSG_GROUP_DUPL;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcom.operacional.dto.ItemDto;
import com.gcom.operacional.service.UniversalGroupsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${gcom.base-uri}/groups")
public class UniversalGroupsControllerImpl implements UniversalGroupsController {
	
	/**
	 * Global variables
	 */
	private final UniversalGroupsService universalGroupsService;
	
	/**
     * Class constructor with @autowire annotation
     * 
     * @param UniversalGroupssService @see {@link UniversalGroupssService}
     */
    public UniversalGroupsControllerImpl(UniversalGroupsService universalGroupsService) {
        this.universalGroupsService = universalGroupsService;
    }

	
	@Override
	@GetMapping("/list")
	public ResponseEntity<List<ItemDto>> getUniversalGroupsList() throws IOException {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<ItemDto> response = universalGroupsService.findAllUniversalGroups();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, response == null || response.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}


	@Override
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody ItemDto request) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		boolean response = false;

		try {
			response = universalGroupsService.save(request);
		} catch (Exception e) {
			if (e.getMessage() == MSG_GROUP_DUPL) {
				return new ResponseEntity<String>(MSG_GROUP_DUPL, HttpStatus.BAD_REQUEST);
			} else {
				throw new Exception(e.getMessage());
			}
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@Override
	@PostMapping("/delete")
	public ResponseEntity<Boolean> delete(@RequestBody ItemDto request) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		boolean response = false;

		try {
			response = universalGroupsService.delete(request);
		} catch (Exception e) {
				throw new Exception(e.getMessage());
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
