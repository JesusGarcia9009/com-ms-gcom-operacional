package com.gcom.operacional.service;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;
import static com.gcom.operacional.utils.ConstantUtil.MSG_BRAND_DUPL;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcom.operacional.dto.ItemDto;
import com.gcom.operacional.entity.Brand;
import com.gcom.operacional.repository.IBrandRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * BrandServiceImpl clase que implementa la interfaz de servicio
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Service
@Slf4j
public class BrandServiceImpl implements BrandService {

	@Autowired
	private IBrandRepository brandRepository;
	
	@Override
	public List<ItemDto> findAllBrand() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<ItemDto> result = brandRepository.findAllBrands();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	@Override
	public Boolean save(ItemDto brand) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		Brand model = brandRepository.findByCode(brand.getCode());
		
		if ((Objects.nonNull(model) && Objects.isNull(brand.getId()))
				|| Objects.nonNull(model) && !brand.getId().equals(model.getId()))
			throw new Exception(MSG_BRAND_DUPL);

		    Brand brandModel = new Brand();
		
			if(Objects.nonNull(brand.getId()))
				brandModel.setId(brand.getId());
				
			brandModel.setCode(brand.getCode());
			brandModel.setDescription(brand.getDescription());
			
			brandRepository.save(brandModel);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

	@Override
	public boolean delete(ItemDto dto) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		brandRepository.deleteById(dto.getId());
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

}
