package com.disoraya.sales_system.endpoint.product.service.impl;

import com.disoraya.sales_system.endpoint.product.dto.InpProductDetDto;
import com.disoraya.sales_system.endpoint.product.dto.InpProductDetWithProdIdDto;
import com.disoraya.sales_system.endpoint.product.dto.OutProductDetDto;
import com.disoraya.sales_system.endpoint.product.dto.projection.ProductDetailSimpleView;
import com.disoraya.sales_system.endpoint.product.dto.projection.ProductDetailView;
import com.disoraya.sales_system.endpoint.product.mapper.ProductDetailMapper;
import com.disoraya.sales_system.endpoint.product.model.Product;
import com.disoraya.sales_system.endpoint.product.model.ProductDetail;
import com.disoraya.sales_system.endpoint.product.model.ProductDetail_;
import com.disoraya.sales_system.endpoint.product.model.Product_;
import com.disoraya.sales_system.endpoint.product.repository.ProductDetailRepository;
import com.disoraya.sales_system.endpoint.product.repository.ProductRepository;
import com.disoraya.sales_system.endpoint.product.repository.view.ProductDetailViewRepository;
import com.disoraya.sales_system.endpoint.product.service.ProductDetailService;
import com.disoraya.sales_system.exception.DuplicateException;
import com.disoraya.sales_system.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductDetailServiceImpl implements ProductDetailService {
  private final ProductRepository productRepo;
  private final ProductDetailRepository productDetailRepo;
  private final ProductDetailViewRepository productDetailViewRepo;
  private final ProductDetailMapper mapper;

  @Override
  public List<ProductDetailView> getAllVisible() {
    return productDetailViewRepo.findAllByIsHidden(false);
  }

  @Override
  public String getAvailableNameById(Integer id) {
    if (productDetailRepo.existsById(id)) {
      throw new DuplicateException("Catalog detail with the given ID already exists");
    }
    return productRepo.findNameById(id).orElseThrow(
        () -> new NotFoundException("Product with the given ID does not exist")
    );
  }

  @Override
  public ProductDetailView getById(Integer id) {
    return productDetailViewRepo.findById(id).orElseThrow(
        () -> new NotFoundException("Catalog detail with the given ID does not exist")
    );
  }

  @Override
  public Slice<ProductDetailSimpleView> getAllByIsHidden(Boolean isHidden, Integer page, Integer size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(
        Sort.Order.asc(ProductDetail_.PRODUCT + "." + Product_.NAME),
        Sort.Order.asc(ProductDetail_.ID)
    ));
    return productDetailViewRepo.findByIsHidden(isHidden, pageable);
  }

  @Override
  @Transactional
  public OutProductDetDto create(InpProductDetWithProdIdDto productDetailDto) {
    Integer id = productDetailDto.getProductId();
    String productName = this.getAvailableNameById(id);

    Product productRef = productRepo.getReferenceById(id);
    ProductDetail productDetail = productDetailRepo.save(
        mapper.withProdIdDtoToEntity(productDetailDto, productRef)
    );
    return mapper.entityToDto(productDetail, productName);
  }

  @Override
  @Transactional
  public OutProductDetDto updateById(Integer id, InpProductDetDto productDetailDto) {
    ProductDetail productDetail = productDetailRepo.findById(id).orElseThrow(
        () -> new NotFoundException("Catalog detail with the given ID does not exist")
    );
    mapper.entityFromDto(productDetailDto, productDetail);

    return mapper.entityToDto(productDetail, null);
  }

  @Override
  @Transactional
  public void deleteById(Integer id) {
    if (!productDetailRepo.existsById(id)) {
      throw new NotFoundException("Catalog detail with the given ID does not exist");
    }
    productDetailRepo.deleteSearchingById(id);
  }
}
