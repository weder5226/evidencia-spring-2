package com.disoraya.sales_system.endpoint.sale.dto;

import com.disoraya.sales_system.validation.ValidationMessage;
import com.disoraya.sales_system.validation.group.OnCreate;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class InpSaleDetDto {
  @Min(value = 1, message = ValidationMessage.MIN)
  @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
  private Integer productId;

  @Max(value = 500, message = ValidationMessage.MAX)
  @Min(value = 1, message = ValidationMessage.MIN)
  @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
  private Integer quantitySold;

  @Digits(integer = 7, fraction = 2, message = ValidationMessage.DIGITS)
  @PositiveOrZero(message = ValidationMessage.POSITIVE_OR_ZERO)
  @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
  private BigDecimal unitPriceSold;
}
