package com.gcom.operacional.service;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;
import static com.gcom.operacional.utils.ConstantUtil.MSG_PROVIDER_DUPL;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcom.operacional.dto.ProviderDto;
import com.gcom.operacional.entity.Contact;
import com.gcom.operacional.entity.Provider;
import com.gcom.operacional.entity.ProvinceOrState;
import com.gcom.operacional.repository.IContactRepository;
import com.gcom.operacional.repository.IProviderRepository;
import com.gcom.operacional.repository.IProvinceOrStateRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * ProviderServiceImpl clase que implementa la interfaz de servicio
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Service
@Slf4j
public class ProviderServiceImpl implements ProviderService {

	@Autowired
	private IProviderRepository providerRepository;
	
	@Autowired
	private IProvinceOrStateRepository provinceOrStateRepository;
	
	@Autowired
	private IContactRepository contactRepository;
	
	@Override
	public List<ProviderDto> findAllProvider() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<ProviderDto> result = providerRepository.findAllProviders();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	@Override
	public Boolean save(ProviderDto provider) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		Provider model = providerRepository.findByRut(provider.getRutOrId());
		
		if ((Objects.nonNull(model) && Objects.isNull(provider.getId()))
				|| Objects.nonNull(model) && !provider.getId().equals(model.getId()))
			throw new Exception(MSG_PROVIDER_DUPL);

		Provider providerModel = new Provider();
		
		Optional<ProvinceOrState> optProvince = provinceOrStateRepository.findById(provider.getProvinceOrStateId());

		if (optProvince.isPresent()) {

			if(Objects.nonNull(provider.getId()))
				providerModel.setId(provider.getId());
				
			providerModel.setRutOrId(provider.getRutOrId());
			providerModel.setFantasyName(provider.getFantasyName());
			providerModel.setBusinessName(provider.getBusinessName());
			providerModel.setAddress(provider.getAddress());
			providerModel.setTransport(provider.getTransport());
			providerModel.setDeliveryObservation(provider.getDeliveryObservation());
			providerModel.setAttachedDocument(provider.getAttachedDocument());
			
			Contact contacModel = new Contact();
			if(Objects.nonNull(provider.getContactid()))
				contacModel.setId(provider.getContactid());
			
			contacModel.setContactName(provider.getContactName());
			contacModel.setPhone(provider.getContactphone());
			contacModel.setCellPhone(provider.getContactcellPhone());
			contacModel.setBusinessMail(provider.getContactbusinessMail());
			
			contacModel = contactRepository.save(contacModel);
			
			providerModel.setContact(contacModel);
			providerModel.setProvinceOrState(optProvince.get());
			
			providerRepository.save(providerModel);
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return true;
	}

	@Override
	public boolean delete(ProviderDto dto) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		providerRepository.deleteById(dto.getId());
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return true;
	}

}
