package com.disoraya.sales_system.endpoint.sale.dto;

import com.disoraya.sales_system.validation.ValidationMessage;
import com.disoraya.sales_system.validation.group.OnCreate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class InpSaleDto {
  @PastOrPresent(message = ValidationMessage.PAST_OR_PRESENT)
  @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
  private LocalDate saleDate;

  private LocalDate deliveryDate;

  @Min(value = 1, message = ValidationMessage.MIN)
  @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
  private Integer clientId;
}
