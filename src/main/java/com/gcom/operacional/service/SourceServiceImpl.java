package com.gcom.operacional.service;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;
import static com.gcom.operacional.utils.ConstantUtil.MSG_SOURCE_DUPL;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcom.operacional.dto.ItemDto;
import com.gcom.operacional.entity.Sources;
import com.gcom.operacional.repository.ISourceRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * SourceServiceImpl clase que implementa la interfaz de servicio
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Service
@Slf4j
public class SourceServiceImpl implements SourceService {

	@Autowired
	private ISourceRepository sourceRepository;
	
	@Override
	public List<ItemDto> findAllSource() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<ItemDto> result = sourceRepository.findAllSources();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	@Override
	public Boolean save(ItemDto source) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		Sources model = sourceRepository.findByCode(source.getCode());
		
		if ((Objects.nonNull(model) && Objects.isNull(source.getId()))
				|| Objects.nonNull(model) && !source.getId().equals(model.getId()))
			throw new Exception(MSG_SOURCE_DUPL);

		    Sources sourceModel = new Sources();
		
			if(Objects.nonNull(source.getId()))
				sourceModel.setId(source.getId());
				
			sourceModel.setCode(source.getCode());
			sourceModel.setDescription(source.getDescription());
			
			sourceRepository.save(sourceModel);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

	@Override
	public boolean delete(ItemDto dto) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		sourceRepository.deleteById(dto.getId());
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

}
