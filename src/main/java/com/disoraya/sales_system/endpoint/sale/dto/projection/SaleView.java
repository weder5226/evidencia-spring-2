package com.disoraya.sales_system.endpoint.sale.dto.projection;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.Mapping;
import com.disoraya.sales_system.endpoint.client.model.Client_;
import com.disoraya.sales_system.endpoint.sale.model.Sale;
import com.disoraya.sales_system.endpoint.sale.model.Sale_;

import java.time.LocalDate;
import java.util.Set;

@EntityView(Sale.class)
public interface SaleView {
  @IdMapping
  Integer getId();

  LocalDate getSaleDate();

  LocalDate getDeliveryDate();

  @Mapping("CONCAT(" +
      Sale_.CLIENT + "." + Client_.FIRST_NAME + ", ' ', " +
      Sale_.CLIENT + "." + Client_.LAST_NAME + ")")
  String getName();

  @Mapping(Sale_.CLIENT + "." + Client_.ID_NUMBER)
  String getIdNumber();

  Set<SaleDetailView> getDetails();
}