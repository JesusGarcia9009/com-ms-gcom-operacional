package com.gcom.operacional.service;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;
import static com.gcom.operacional.utils.ConstantUtil.MSG_PRODUCT_TYPE_DUPL;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcom.operacional.dto.ItemDto;
import com.gcom.operacional.entity.ProductType;
import com.gcom.operacional.repository.IProductTypeRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * ProductTypeServiceImpl clase que implementa la interfaz de servicio
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Service
@Slf4j
public class ProductTypeServiceImpl implements ProductTypeService {

	@Autowired
	private IProductTypeRepository typeRepository;
	
	@Override
	public List<ItemDto> findAllProductType() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<ItemDto> result = typeRepository.findAllProductTypes();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	@Override
	public Boolean save(ItemDto type) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		ProductType model = typeRepository.findByCode(type.getCode());
		
		if ((Objects.nonNull(model) && Objects.isNull(type.getId()))
				|| Objects.nonNull(model) && !type.getId().equals(model.getId()))
			throw new Exception(MSG_PRODUCT_TYPE_DUPL);

		    ProductType typeModel = new ProductType();
		
			if(Objects.nonNull(type.getId()))
				typeModel.setId(type.getId());
				
			typeModel.setCode(type.getCode());
			typeModel.setDescription(type.getDescription());
			
			typeRepository.save(typeModel);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

	@Override
	public boolean delete(ItemDto dto) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		typeRepository.deleteById(dto.getId());
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

}
