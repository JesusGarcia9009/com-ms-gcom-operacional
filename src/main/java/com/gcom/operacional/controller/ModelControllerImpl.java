package com.gcom.operacional.controller;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;
import static com.gcom.operacional.utils.ConstantUtil.MSG_MODEL_DUPL;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcom.operacional.dto.ModelDto;
import com.gcom.operacional.service.ModelService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${gcom.base-uri}/model")
public class ModelControllerImpl implements ModelController {

	/**
	 * Global variables
	 */
	private final ModelService modelService;

	/**
	 * Class constructor with @autowire annotation
	 * 
	 * @param ModelsService @see {@link ModelsService}
	 */
	public ModelControllerImpl(ModelService modelService) {
		this.modelService = modelService;
	}

	@Override
	@GetMapping("/list-by/{idBrand}")
	public ResponseEntity<List<ModelDto>> getListByBrand(@PathVariable Long idBrand) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<ModelDto> response = modelService.getListByBrand(idBrand);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response,
				response == null || response.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}

	@Override
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody ModelDto request) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		boolean response = false;

		try {
			response = modelService.save(request);
		} catch (Exception e) {
			if (e.getMessage() == MSG_MODEL_DUPL) {
				return new ResponseEntity<String>(MSG_MODEL_DUPL, HttpStatus.BAD_REQUEST);
			} else {
				throw new Exception(e.getMessage());
			}
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	@PostMapping("/delete")
	public ResponseEntity<Boolean> delete(@RequestBody ModelDto request) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		boolean response = false;

		try {
			response = modelService.delete(request);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	

}
