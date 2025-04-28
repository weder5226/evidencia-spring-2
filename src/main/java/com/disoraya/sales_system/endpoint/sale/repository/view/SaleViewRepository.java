package com.disoraya.sales_system.endpoint.sale.repository.view;

import com.disoraya.sales_system.endpoint.sale.dto.projection.SaleSimpleView;
import com.disoraya.sales_system.endpoint.sale.dto.projection.SaleView;
import com.disoraya.sales_system.endpoint.sale.model.Sale;
import com.disoraya.sales_system.endpoint.sale.specification.FilterSaleSpec;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface SaleViewRepository extends Repository<Sale, Integer> {
  Optional<SaleView> findById(Integer id);

  Slice<SaleSimpleView> findBy(FilterSaleSpec specification, Pageable pageable);
}