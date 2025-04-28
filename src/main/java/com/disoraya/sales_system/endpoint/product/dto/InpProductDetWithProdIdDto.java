package com.disoraya.sales_system.endpoint.product.dto;

import com.disoraya.sales_system.validation.ValidationMessage;
import com.disoraya.sales_system.validation.group.OnCreate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InpProductDetWithProdIdDto extends InpProductDetDto {
  @Min(value = 1, message = ValidationMessage.MIN)
  @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
  private Integer productId;
}
