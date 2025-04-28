package com.disoraya.sales_system.endpoint.client.dto.param;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FilterClientDto {
  private String firstNamePrefix;
  private String lastNamePrefix;
  private String idNumberPrefix;
}
