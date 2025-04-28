package com.disoraya.sales_system.endpoint.product.service;

import com.disoraya.sales_system.endpoint.product.dto.InpProductDetDto;
import com.disoraya.sales_system.endpoint.product.dto.InpProductDetWithProdIdDto;
import com.disoraya.sales_system.endpoint.product.dto.OutProductDetDto;
import com.disoraya.sales_system.endpoint.product.dto.projection.ProductDetailSimpleView;
import com.disoraya.sales_system.endpoint.product.dto.projection.ProductDetailView;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ProductDetailService {
  List<ProductDetailView> getAllVisible();

  String getAvailableNameById(Integer id);

  ProductDetailView getById(Integer id);

  Slice<ProductDetailSimpleView> getAllByIsHidden(Boolean isHidden, Integer page, Integer size);

  OutProductDetDto create(InpProductDetWithProdIdDto productDetailDto);

  OutProductDetDto updateById(Integer id, InpProductDetDto productDetailDto);

  void deleteById(Integer id);
}
