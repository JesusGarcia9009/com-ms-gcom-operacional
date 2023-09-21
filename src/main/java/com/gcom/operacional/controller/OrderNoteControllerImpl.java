package com.gcom.operacional.controller;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gcom.operacional.dto.OrderNoteBIllDto;
import com.gcom.operacional.dto.OrderNoteDto;
import com.gcom.operacional.dto.OrderNoteReverseDto;
import com.gcom.operacional.dto.QuotationAutoCompleteDto;
import com.gcom.operacional.service.OrderNoteService;
import com.gcom.operacional.token.JwtUsuario;
import com.gcom.operacional.token.UserPrincipal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${gcom.base-uri}/order-note")
public class OrderNoteControllerImpl implements OrderNoteController {
	
	/**
	 * Global variables
	 */
	private final OrderNoteService orderNoteService;
	
	/**
     * Class constructor with @autowire annotation
     * 
     * @param OrderNotesService @see {@link OrderNotesService}
     */
    public OrderNoteControllerImpl(OrderNoteService orderNoteService) {
        this.orderNoteService = orderNoteService;
    }
    
    @Override
    @GetMapping(path = "/complete")
	public ResponseEntity<List<QuotationAutoCompleteDto>> findByCode(@Valid @RequestParam("code") String code) throws Exception {
    	log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
    	List<QuotationAutoCompleteDto> result = orderNoteService.findByCode(code);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return ResponseEntity.ok(result);
	}
	
	@Override
	@GetMapping("/list")
	public ResponseEntity<List<OrderNoteDto>> findAll() throws IOException {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<OrderNoteDto> response = orderNoteService.findAll();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, response == null || response.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
	
	@Override
	@GetMapping(path = "/export/{id}")
	public ResponseEntity<byte[]> getOrderNoteDocument(@PathVariable Long id,@JwtUsuario UserPrincipal token, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		byte[] result = orderNoteService.getOrderNoteDocument(id, token, request, response);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return ResponseEntity.ok(result);
	}

	@Override
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody OrderNoteDto request, @JwtUsuario UserPrincipal token) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		boolean response = orderNoteService.save(request, token);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@Override
	@PostMapping("/reverse")
	public ResponseEntity<?> reverse(@RequestBody OrderNoteReverseDto request,@JwtUsuario UserPrincipal token) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		boolean response = orderNoteService.reverse(request, token);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@Override
	@PostMapping("/bill")
	public ResponseEntity<?> bill(@RequestBody OrderNoteBIllDto request,@JwtUsuario UserPrincipal token) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		boolean response = orderNoteService.bill(request, token);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@Override
	@PostMapping("/delete")
	public ResponseEntity<Boolean> delete(@RequestBody OrderNoteDto request) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		boolean response = false;

		try {
			response = orderNoteService.delete(request);
		} catch (Exception e) {
				throw new Exception(e.getMessage());
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return ResponseEntity.ok(response);
	}

	

	
	
	
}
