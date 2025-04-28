package com.disoraya.sales_system.endpoint.sale.dto.projection;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.Mapping;
import com.disoraya.sales_system.endpoint.client.model.Client_;
import com.disoraya.sales_system.endpoint.sale.model.Sale;
import com.disoraya.sales_system.endpoint.sale.model.SaleDetail_;
import com.disoraya.sales_system.endpoint.sale.model.Sale_;

import java.math.BigDecimal;
import java.time.LocalDate;

@EntityView(Sale.class)
public interface SaleSimpleView {
  @IdMapping
  Integer getId();

  LocalDate getSaleDate();

  LocalDate getDeliveryDate();

  @Mapping(Sale_.CLIENT + "." + Client_.ID_NUMBER)
  String getClientIdNumber();

  @Mapping("SUM(CAST_BIGDECIMAL(COALESCE(" +
      Sale_.DETAILS + "." + SaleDetail_.QUANTITY_SOLD + " * " +
      Sale_.DETAILS + "." + SaleDetail_.UNIT_PRICE_SOLD + ", 0)))"
  )
  BigDecimal getTotal();
}