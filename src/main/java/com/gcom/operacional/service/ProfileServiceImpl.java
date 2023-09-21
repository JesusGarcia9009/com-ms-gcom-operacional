package com.gcom.operacional.service;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcom.operacional.repository.IProfileRepository;
import com.gcom.operacional.token.ProfileDto;

import lombok.extern.slf4j.Slf4j;

/**
 * ProfileService clase que implementa la interfaz de servicio
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Service
@Slf4j
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	private IProfileRepository profileRepository;
	
	@Override
	public List<ProfileDto> findAllProfiles() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<ProfileDto> result = profileRepository.findProfileList();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	
}
