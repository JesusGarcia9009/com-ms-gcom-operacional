package com.gcom.operacional.controller;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;
import static com.gcom.operacional.utils.ConstantUtil.MSG_MAIL_DUPL;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcom.operacional.dto.UserDto;
import com.gcom.operacional.service.ProfileService;
import com.gcom.operacional.service.UsersService;
import com.gcom.operacional.token.ProfileDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${gcom.base-uri}/user")
public class UserControllerImpl implements UserController {
	
	/**
	 * Global variables
	 */
	private final UsersService usersService;
	private final ProfileService profileService;
	private final PasswordEncoder passwordEncoder;
	
	/**
     * Class constructor with @autowire annotation
     * 
     * @param UsersService @see {@link UsersService}
     */
    public UserControllerImpl(UsersService usersService, ProfileService profileService, PasswordEncoder passwordEncoder) {
        this.usersService = usersService;
        this.profileService = profileService;
        this.passwordEncoder = passwordEncoder;
    }

	
	@Override
	@GetMapping("/list")
	public ResponseEntity<List<UserDto>> getUserList() throws IOException {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<UserDto> response = usersService.findAllUsers();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, response == null || response.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}


	@Override
	@GetMapping("/list-profile")
	public ResponseEntity<List<ProfileDto>> getProfileList() throws IOException {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<ProfileDto> response = profileService.findAllProfiles();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, response == null || response.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}


	@Override
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody UserDto request) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		boolean response = false;

		try {
			request.setPass(passwordEncoder.encode(request.getPass()));
			response = usersService.save(request);
		} catch (Exception e) {
			if (e.getMessage() == MSG_MAIL_DUPL) {
				return new ResponseEntity<String>(MSG_MAIL_DUPL, HttpStatus.BAD_REQUEST);
			} else {
				throw new Exception(e.getMessage());
			}
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@Override
	@PostMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody UserDto request) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		boolean response = false;

		try {
			response = usersService.delete(request);
		} catch (Exception e) {
				throw new Exception(e.getMessage());
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
