package com.gcom.operacional.service;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;
import static com.gcom.operacional.utils.ConstantUtil.MSG_MODEL_DUPL;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcom.operacional.dto.ModelDto;
import com.gcom.operacional.entity.Brand;
import com.gcom.operacional.entity.Model;
import com.gcom.operacional.repository.IBrandRepository;
import com.gcom.operacional.repository.IModelRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * ModelServiceImpl clase que implementa la interfaz de servicio
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Service
@Slf4j
public class ModelServiceImpl implements ModelService {

	@Autowired
	private IModelRepository modelRepository;
	
	@Autowired
	private IBrandRepository brandRepository;
	
	@Override
	public List<ModelDto> getListByBrand(Long idBrand) {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<ModelDto> result = null;
		if(idBrand == -1l)
			result =  modelRepository.findModelsByBrand(null);
		else
			result = modelRepository.findModelsByBrand(idBrand);
		
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	@Override
	public Boolean save(ModelDto dto) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		Model model = modelRepository.findByCode(dto.getCode());
		
		if ((Objects.nonNull(model) && Objects.isNull(dto.getId()))
				|| Objects.nonNull(model) && !dto.getId().equals(model.getId()))
			throw new Exception(MSG_MODEL_DUPL);

		    Model entity = new Model();
		    
		    Optional<Brand> optBrand = brandRepository.findById(dto.getBrandId());

			if (optBrand.isPresent()) {
				if(Objects.nonNull(dto.getId()))
					entity.setId(dto.getId());
					
				entity.setCode(dto.getCode());
				entity.setDescription(dto.getDescription());
				entity.setMeasure(dto.getMeasure());
				entity.setVehicleType(dto.getVehicleType());
				entity.setApproximateYear(dto.getApproximateYear());
				entity.setEngineDescription(dto.getEngineDescription());
				entity.setTypeOfMotor(dto.getTypeOfMotor());
				entity.setNotes(dto.getNotes());
				entity.setMast(dto.getMast());
				entity.setBrand(optBrand.get());
			}else {
				return Boolean.FALSE;
			}
			modelRepository.save(entity);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

	@Override
	public boolean delete(ModelDto dto) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		modelRepository.deleteById(dto.getId());
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

	

}
