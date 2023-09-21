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

import com.gcom.operacional.dto.InventoryTweaksDto;
import com.gcom.operacional.service.InventoryTweaksService;
import com.gcom.operacional.token.JwtUsuario;
import com.gcom.operacional.token.UserPrincipal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${gcom.base-uri}/inventory-tweaks")
public class InventoryTweaksControllerImpl implements InventoryTweaksController {
	
	/**
	 * Global variables
	 */
	private final InventoryTweaksService inventoryTweaksService;
	
	/**
     * Class constructor with @autowire annotation
     * 
     * @param InventoryTweakssService @see {@link InventoryTweakssService}
     */
    public InventoryTweaksControllerImpl(InventoryTweaksService inventoryTweaksService) {
        this.inventoryTweaksService = inventoryTweaksService;
    }

	
	@Override
	@GetMapping("/list")
	public ResponseEntity<List<InventoryTweaksDto>> getInventoryTweaksList() throws IOException {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<InventoryTweaksDto> response = inventoryTweaksService.findAllInventoryTweaks();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, response == null || response.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}


	@Override
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody InventoryTweaksDto request, @JwtUsuario UserPrincipal token) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		boolean response = false;

		try {
			response = inventoryTweaksService.save(request, token);
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
	@PostMapping("/implement")
	public ResponseEntity<?> implement(@RequestBody InventoryTweaksDto request, @JwtUsuario UserPrincipal token) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return ResponseEntity.ok(inventoryTweaksService.implement(request, token));
	}
	
	@Override
	@PostMapping("/delete")
	public ResponseEntity<Boolean> delete(@RequestBody InventoryTweaksDto request, @JwtUsuario UserPrincipal token) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		boolean response = false;

		try {
			response = inventoryTweaksService.delete(request, token);
		} catch (Exception e) {
				throw new Exception(e.getMessage());
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	
	
}
