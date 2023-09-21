package com.gcom.operacional.service;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;
import static com.gcom.operacional.utils.ConstantUtil.MSG_CLIENT_DUPL;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcom.operacional.dto.ClientDto;
import com.gcom.operacional.entity.Client;
import com.gcom.operacional.entity.Contact;
import com.gcom.operacional.entity.DeliveryType;
import com.gcom.operacional.entity.PaymentMethod;
import com.gcom.operacional.entity.ProvinceOrState;
import com.gcom.operacional.repository.IClientRepository;
import com.gcom.operacional.repository.IContactRepository;
import com.gcom.operacional.repository.IDeliveryTypeRepository;
import com.gcom.operacional.repository.IPaymentMethodRepository;
import com.gcom.operacional.repository.IProvinceOrStateRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * ClientServiceImpl clase que implementa la interfaz de servicio
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

	@Autowired
	private IClientRepository clientRepository;
	
	@Autowired
	private IProvinceOrStateRepository provinceOrStateRepository;
	
	@Autowired
	private IPaymentMethodRepository paymentMethodRepository;
	
	@Autowired
	private IDeliveryTypeRepository deliveryTypeRepository;
	
	@Autowired
	private IContactRepository contactRepository;
	
	@Override
	public List<ClientDto> findAllClient() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<ClientDto> result = clientRepository.findAllClients();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}
	
	@Override
	public ClientDto findClientById(Long id) {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		ClientDto result = clientRepository.findClientById(id);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	@Override
	public Boolean save(ClientDto client) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		Client model = clientRepository.findByRut(client.getRutOrId());
		
		if ((Objects.nonNull(model) && Objects.isNull(client.getId()))
				|| Objects.nonNull(model) && !client.getId().equals(model.getId()))
			throw new Exception(MSG_CLIENT_DUPL);

		Client clientModel = new Client();
		
		Optional<ProvinceOrState> optProvince = provinceOrStateRepository.findById(client.getProvinceOrStateId());
		Optional<DeliveryType> optDelivery = deliveryTypeRepository.findById(client.getDeliveryTypeId());
		Optional<PaymentMethod> optPayment = paymentMethodRepository.findById(client.getPaymentMethodId());

		if (optPayment.isPresent() && optDelivery.isPresent() && optProvince.isPresent()) {

			if(Objects.nonNull(client.getId()))
				clientModel.setId(client.getId());
				
			clientModel.setRutOrId(client.getRutOrId());
			clientModel.setFantasyName(client.getFantasyName());
			clientModel.setBusinessName(client.getBusinessName());
			clientModel.setAddress(client.getAddress());
			clientModel.setTransport(client.getTransport());
			clientModel.setDeliveryObservation(client.getDeliveryObservation());
			clientModel.setAttachedDocument(client.getAttachedDocument());
			
			Contact contacModel = new Contact();
			if(Objects.nonNull(client.getContactid()))
				contacModel.setId(client.getContactid());
			
			contacModel.setContactName(client.getContactName());
			contacModel.setPhone(client.getContactphone());
			contacModel.setCellPhone(client.getContactcellPhone());
			contacModel.setBusinessMail(client.getContactbusinessMail());
			
			contacModel = contactRepository.save(contacModel);
			
			clientModel.setContact(contacModel);
			clientModel.setProvinceOrState(optProvince.get());
			clientModel.setDeliveryType(optDelivery.get());
			clientModel.setPaymentMethod(optPayment.get());
			
			
			clientRepository.save(clientModel);
			return true;
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return false;
	}

	@Override
	public boolean delete(ClientDto dto) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		clientRepository.deleteById(dto.getId());
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return true;
	}

	

}
