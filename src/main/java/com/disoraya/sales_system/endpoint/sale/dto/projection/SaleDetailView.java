package com.disoraya.sales_system.endpoint.sale.dto.projection;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.Mapping;
import com.disoraya.sales_system.endpoint.product.model.Product_;
import com.disoraya.sales_system.endpoint.sale.model.SaleDetail;
import com.disoraya.sales_system.endpoint.sale.model.SaleDetailId_;
import com.disoraya.sales_system.endpoint.sale.model.SaleDetail_;

import java.math.BigDecimal;

@EntityView(SaleDetail.class)
public interface SaleDetailView {
  @IdMapping()
  @Mapping(SaleDetail_.ID + "." + SaleDetailId_.PRODUCT_ID)
  Integer getId();

  Integer getQuantitySold();

  BigDecimal getUnitPriceSold();

  @Mapping(SaleDetail_.PRODUCT + "." + Product_.NAME)
  String getProductName();
}