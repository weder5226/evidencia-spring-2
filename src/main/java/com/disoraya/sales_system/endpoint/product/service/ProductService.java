package com.disoraya.sales_system.endpoint.product.service;

import com.disoraya.sales_system.endpoint.product.dto.InpProductDto;
import com.disoraya.sales_system.endpoint.product.dto.OutProductDto;
import com.disoraya.sales_system.endpoint.product.dto.projection.ProductView;
import org.springframework.data.domain.Slice;

public interface ProductService {
  ProductView getById(Integer id);

  Slice<ProductView> getAllByHasStock(Integer page, Integer size, Boolean hasStock);

  OutProductDto create(InpProductDto productDto);

  OutProductDto updateById(Integer id, InpProductDto productDto);

  void deleteById(Integer id);
}
