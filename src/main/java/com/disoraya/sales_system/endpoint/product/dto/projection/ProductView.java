package com.disoraya.sales_system.endpoint.product.dto.projection;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.disoraya.sales_system.endpoint.product.model.Product;

@EntityView(Product.class)
public interface ProductView {
  @IdMapping
  Integer getId();

  String getName();

  Integer getQuantityStock();
}