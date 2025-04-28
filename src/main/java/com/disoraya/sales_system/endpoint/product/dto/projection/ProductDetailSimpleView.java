package com.disoraya.sales_system.endpoint.product.dto.projection;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.Mapping;
import com.disoraya.sales_system.endpoint.product.model.ProductDetail;

import java.math.BigDecimal;

@EntityView(ProductDetail.class)
public interface ProductDetailSimpleView {
  @IdMapping
  Integer getId();

  @Mapping("product.name")
  String getName();

  String getDetail();

  BigDecimal getPrice();
}