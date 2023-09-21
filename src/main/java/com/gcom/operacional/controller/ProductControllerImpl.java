package com.gcom.operacional.controller;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;
import static com.gcom.operacional.utils.ConstantUtil.MSG_PRODUCT_DUPL;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcom.operacional.dto.ProductDto;
import com.gcom.operacional.dto.ProductSelectDto;
import com.gcom.operacional.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${gcom.base-uri}/product")
public class ProductControllerImpl implements ProductController {
	
	/**
	 * Global variables
	 */
	private final ProductService productService;
	
	/**
     * Class constructor with @autowire annotation
     * 
     * @param ProductsService @see {@link ProductsService}
     */
    public ProductControllerImpl(ProductService productService) {
        this.productService = productService;
    }

	
	@Override
	@GetMapping("/list")
	public ResponseEntity<List<ProductDto>> getProductList() throws IOException {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<ProductDto> response = productService.findAllProduct();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, response == null || response.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
	
	@Override
	@GetMapping("/list-select")
	public ResponseEntity<List<ProductSelectDto>> getProductSelectList() throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<ProductSelectDto> response = productService.findAllProductSelect();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, response == null || response.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}

	
	@Override
	@GetMapping("/find-by-id/{productId}")
	public ResponseEntity<ProductDto> findById(@PathVariable Long productId) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		ProductDto response = productService.findById(productId);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	@Override
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody ProductDto request) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		boolean response = false;

		try {
			response = productService.save(request);
		} catch (Exception e) {
			if (e.getMessage() == MSG_PRODUCT_DUPL) {
				return new ResponseEntity<String>(MSG_PRODUCT_DUPL, HttpStatus.BAD_REQUEST);
			} else {
				throw new Exception(e.getMessage());
			}
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@Override
	@PostMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody ProductDto request) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		boolean response = false;

		try {
			response = productService.delete(request);
		} catch (Exception e) {
				throw new Exception(e.getMessage());
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


}
