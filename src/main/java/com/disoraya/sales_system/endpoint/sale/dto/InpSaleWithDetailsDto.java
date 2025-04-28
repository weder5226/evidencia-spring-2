package com.disoraya.sales_system.endpoint.sale.dto;

import com.disoraya.sales_system.validation.ValidationMessage;
import com.disoraya.sales_system.validation.group.OnCreate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class InpSaleWithDetailsDto extends InpSaleDto {
  @Size(min = 1, max = 100, message = ValidationMessage.SIZE)
  @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
  private List<@Valid @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class) InpSaleDetDto> details;
}
