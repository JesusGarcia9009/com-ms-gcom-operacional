package com.gcom.operacional.service;

import static com.gcom.operacional.utils.ConstantUtil.LOG_END;
import static com.gcom.operacional.utils.ConstantUtil.LOG_START;
import static com.gcom.operacional.utils.ConstantUtil.MSG_PRODUCT_DUPL;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcom.operacional.dto.ProductDto;
import com.gcom.operacional.dto.ProductSelectDto;
import com.gcom.operacional.entity.Gloss;
import com.gcom.operacional.entity.Model;
import com.gcom.operacional.entity.OriginalCode;
import com.gcom.operacional.entity.Product;
import com.gcom.operacional.entity.ProductType;
import com.gcom.operacional.entity.ProviderCode;
import com.gcom.operacional.entity.Sources;
import com.gcom.operacional.entity.UniversalGroups;
import com.gcom.operacional.repository.IGlossRepository;
import com.gcom.operacional.repository.IModelRepository;
import com.gcom.operacional.repository.IOriginalCodeRepository;
import com.gcom.operacional.repository.IProductRepository;
import com.gcom.operacional.repository.IProductTypeRepository;
import com.gcom.operacional.repository.IProviderCodeRepository;
import com.gcom.operacional.repository.ISourceRepository;
import com.gcom.operacional.repository.IUniversalGroupsRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * ProductServiceImpl clase que implementa la interfaz de servicio
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	@Autowired
	private IProductRepository productRepository;

	@Autowired
	private IModelRepository modelRepository;

	@Autowired
	private ISourceRepository sourceRepository;

	@Autowired
	private IUniversalGroupsRepository groupRepository;

	@Autowired
	private IProductTypeRepository typeRepository;

	@Autowired
	private IGlossRepository glossRepository;

	@Autowired
	private IProviderCodeRepository providerCodeRepository;

	@Autowired
	private IOriginalCodeRepository originalCodeRepository;

	@Override
	public List<ProductDto> findAllProduct() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<ProductDto> result = productRepository.findAllProducts();
		for (ProductDto productDto : result) {
			productDto.setGlossList(productRepository.findGlossList(productDto.getId()));
			productDto.setProviderCodeList(productRepository.findProviderCodeList(productDto.getId()));
			productDto.setOriginalCodeList(productRepository.findOriginalCodeList(productDto.getId()));
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}
	
	@Override
	public List<ProductSelectDto> findAllProductSelect() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<ProductSelectDto> result = productRepository.findAllProductSelect();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	@Override
	public ProductDto findById(Long idProduct) {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		ProductDto productDto = productRepository.findByIdProduct(idProduct);
		productDto.setGlossList(productRepository.findGlossList(productDto.getId()));
		productDto.setProviderCodeList(productRepository.findProviderCodeList(productDto.getId()));
		productDto.setOriginalCodeList(productRepository.findOriginalCodeList(productDto.getId()));
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return productDto;
	}

	@Override
	public Boolean save(ProductDto product) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		Product model = productRepository.findByNetCostAndSalePriceAndDescription(product.getNetCost(),
				product.getSalePrice(), product.getDescription());

		if ((Objects.nonNull(model) && Objects.isNull(product.getId()))
				|| Objects.nonNull(model) && !product.getId().equals(model.getId()))
			throw new Exception(MSG_PRODUCT_DUPL);

		Product productModel = new Product();

		Optional<Model> optModel = modelRepository.findById(product.getModelId());
		Optional<Sources> optSource = sourceRepository.findById(product.getSourceId());
		Optional<UniversalGroups> optGroup = groupRepository.findById(product.getUniversalGroupId());
		Optional<ProductType> optType = typeRepository.findById(product.getProductTypeId());

		if (optModel.isPresent() && optSource.isPresent() && optGroup.isPresent() && optType.isPresent()) {

			if (Objects.nonNull(product.getId()))
				productModel.setId(product.getId());
			else
				productModel.setStock(0);
			
			if(Objects.nonNull(product.getProductReferenceId())) {
				Optional<Product> optProduct = productRepository.findById(product.getProductReferenceId());
				if(optProduct.isPresent()) {
					productModel.setProduct(optProduct.get());
				}
			}

			productModel.setSalePrice(product.getSalePrice());
			productModel.setNetCost(product.getNetCost());
			productModel.setRowNumb(product.getRowNumb());
			productModel.setColNumb(product.getColNumb());
			productModel.setDescription(product.getDescription());
			productModel.setProviderDescription(product.getProviderDescription());

			productModel.setModel(optModel.get());
			productModel.setSources(optSource.get());
			productModel.setUniversalGroups(optGroup.get());
			productModel.setProductType(optType.get());

			productModel = productRepository.save(productModel);

			glossRepository.deleteAll(glossRepository.findByProduct(productModel.getId()));
			for (String item : product.getGlossList()) {
				Gloss g = glossRepository.findByDescriptionAndProduct(item, productModel);
				if (Objects.isNull(g)) {
					g = new Gloss();
				}
				g.setDescription(item);
				g.setProduct(productModel);
				glossRepository.save(g);
			}

			providerCodeRepository.deleteAll(providerCodeRepository.findByProduct(productModel.getId()));
			for (String item : product.getProviderCodeList()) {
				ProviderCode g = providerCodeRepository.findByDescription(item);
				if (Objects.isNull(g)) {
					g = new ProviderCode();
				}
				g.setDescription(item);
				g.setProduct(productModel);
				providerCodeRepository.save(g);
			}

			originalCodeRepository.deleteAll(originalCodeRepository.findByProduct(productModel.getId()));
			for (String item : product.getGlossList()) {
				OriginalCode g = originalCodeRepository.findByDescription(item);
				if (Objects.isNull(g)) {
					g = new OriginalCode();
				}
				g.setDescription(item);
				g.setProduct(productModel);
				originalCodeRepository.save(g);
			}
		} else {
			return Boolean.FALSE;
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

	@Override
	public boolean delete(ProductDto dto) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		glossRepository.deleteAll(glossRepository.findByProduct(dto.getId()));
		providerCodeRepository.deleteAll(providerCodeRepository.findByProduct(dto.getId()));
		originalCodeRepository.deleteAll(originalCodeRepository.findByProduct(dto.getId()));
		productRepository.deleteById(dto.getId());
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return Boolean.TRUE;
	}

	

}
