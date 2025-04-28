package com.disoraya.sales_system.endpoint.sale.service;

import com.disoraya.sales_system.endpoint.sale.dto.InpSaleDetWithSaleIdDto;
import com.disoraya.sales_system.endpoint.sale.dto.OutSaleDetDto;

public interface SaleDetailService {
  OutSaleDetDto create(InpSaleDetWithSaleIdDto saleDto);

  void deleteById(Integer saleId, Integer productId);
}
