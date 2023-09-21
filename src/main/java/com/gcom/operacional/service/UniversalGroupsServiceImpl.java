package com.gcom.operacional.service;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;
import static com.gcom.operacional.utils.ConstantUtil.MSG_GROUP_DUPL;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcom.operacional.dto.ItemDto;
import com.gcom.operacional.entity.UniversalGroups;
import com.gcom.operacional.repository.IUniversalGroupsRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * UniversalGroupsServiceImpl clase que implementa la interfaz de servicio
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Service
@Slf4j
public class UniversalGroupsServiceImpl implements UniversalGroupsService {

	@Autowired
	private IUniversalGroupsRepository universalGroupsRepository;
	
	@Override
	public List<ItemDto> findAllUniversalGroups() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<ItemDto> result = universalGroupsRepository.findAllUniversalGroups();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	@Override
	public Boolean save(ItemDto universalGroups) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		UniversalGroups model = universalGroupsRepository.findByCode(universalGroups.getCode());
		
		if ((Objects.nonNull(model) && Objects.isNull(universalGroups.getId()))
				|| Objects.nonNull(model) && !universalGroups.getId().equals(model.getId()))
			throw new Exception(MSG_GROUP_DUPL);

		    UniversalGroups universalGroupsModel = new UniversalGroups();
		
			if(Objects.nonNull(universalGroups.getId()))
				universalGroupsModel.setId(universalGroups.getId());
				
			universalGroupsModel.setCode(universalGroups.getCode());
			universalGroupsModel.setDescription(universalGroups.getDescription());
			
			universalGroupsRepository.save(universalGroupsModel);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

	@Override
	public boolean delete(ItemDto dto) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		universalGroupsRepository.deleteById(dto.getId());
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

}
