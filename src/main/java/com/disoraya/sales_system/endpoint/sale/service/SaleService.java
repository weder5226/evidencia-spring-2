package com.disoraya.sales_system.endpoint.sale.service;

import com.disoraya.sales_system.endpoint.sale.dto.InpSaleDto;
import com.disoraya.sales_system.endpoint.sale.dto.InpSaleWithDetailsDto;
import com.disoraya.sales_system.endpoint.sale.dto.OutSaleDto;
import com.disoraya.sales_system.endpoint.sale.dto.param.FilterSaleDto;
import com.disoraya.sales_system.endpoint.sale.dto.projection.SaleSimpleView;
import com.disoraya.sales_system.endpoint.sale.dto.projection.SaleView;
import org.springframework.data.domain.Slice;

public interface SaleService {
  SaleView getById(Integer id);

  Slice<SaleSimpleView> getAllFiltered(FilterSaleDto filterSaleDto, Integer page, Integer size);

  OutSaleDto create(InpSaleWithDetailsDto saleDto);

  OutSaleDto updateById(Integer id, InpSaleDto saleDto);

  void deleteById(Integer id);
}
