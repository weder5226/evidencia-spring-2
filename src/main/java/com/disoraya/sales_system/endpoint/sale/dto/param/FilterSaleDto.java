package com.disoraya.sales_system.endpoint.sale.dto.param;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class FilterSaleDto {
  private LocalDate minDate;
  private LocalDate maxDate;
  private String idNumberPrefix;
  private Boolean isDelivered;
}
