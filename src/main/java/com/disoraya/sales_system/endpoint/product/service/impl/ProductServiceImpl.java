package com.disoraya.sales_system.endpoint.product.service.impl;

import com.disoraya.sales_system.endpoint.product.dto.InpProductDto;
import com.disoraya.sales_system.endpoint.product.dto.OutProductDto;
import com.disoraya.sales_system.endpoint.product.dto.projection.ProductView;
import com.disoraya.sales_system.endpoint.product.mapper.ProductMapper;
import com.disoraya.sales_system.endpoint.product.model.Product;
import com.disoraya.sales_system.endpoint.product.model.Product_;
import com.disoraya.sales_system.endpoint.product.repository.ProductDetailRepository;
import com.disoraya.sales_system.endpoint.product.repository.ProductRepository;
import com.disoraya.sales_system.endpoint.product.repository.view.ProductViewRepository;
import com.disoraya.sales_system.endpoint.product.service.ProductService;
import com.disoraya.sales_system.exception.DuplicateException;
import com.disoraya.sales_system.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepo;
  private final ProductViewRepository productViewRepo;
  private final ProductDetailRepository productDetailRepo;
  private final ProductMapper mapper;

  @Override
  public ProductView getById(Integer id) {
    return productViewRepo.findById(id).orElseThrow(
        () -> new NotFoundException("Product with the given ID does not exist")
    );
  }

  @Override
  public Slice<ProductView> getAllByHasStock(Integer page, Integer size, Boolean hasStock) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(
        Sort.Order.asc(Product_.NAME),
        Sort.Order.asc(Product_.ID)
    ));
    return hasStock
        ? productViewRepo.findByQuantityStockNot(0, pageable)
        : productViewRepo.findByQuantityStock(0, pageable);
  }

  @Override
  @Transactional
  public OutProductDto create(InpProductDto productDto) {
    if (productRepo.existsByNameIgnoreCase(productDto.name())) {
      throw new DuplicateException("Name is already in use by another product");
    }
    Product product = productRepo.save(mapper.dtoToEntity(productDto));
    return mapper.entityToDto(product);
  }

  @Override
  @Transactional
  public OutProductDto updateById(Integer id, InpProductDto productDto) {
    Product product = productRepo.findById(id).orElseThrow(
        () -> new NotFoundException("Product with the given ID does not exist")
    );
    String newName = productDto.name();
    if (newName != null
        && !newName.equals(product.getName())
        && productRepo.existsByName(newName)
    ) {
      throw new DuplicateException("New name is already in use by another product");
    }
    mapper.entityFromDto(productDto, product);

    return mapper.entityToDto(product);
  }

  @Override
  @Transactional
  public void deleteById(Integer id) {
    if (!productRepo.existsById(id)) {
      throw new NotFoundException("Product with the given ID does not exist");
    }
    productDetailRepo.deleteSearchingById(id);
    productRepo.deleteSearchingById(id);
  }
}
