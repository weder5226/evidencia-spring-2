package com.disoraya.sales_system.endpoint.product.dto;

import com.disoraya.sales_system.validation.ValidationMessage;
import com.disoraya.sales_system.validation.annotation.NoSpacesAtEnds;
import com.disoraya.sales_system.validation.group.OnCreate;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class InpProductDetDto {
  @URL(regexp = "^[^\\s]*$", message = ValidationMessage.URL)
  @Size(min = 10, max = 480, message = ValidationMessage.SIZE)
  @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
  private String imageUrl;

  @NoSpacesAtEnds(message = ValidationMessage.NO_SPACES_AT_ENDS)
  @Size(min = 5, max = 90, message = ValidationMessage.SIZE)
  @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
  private String imageAlt;

  @URL(regexp = "^[^\\s]*$", message = ValidationMessage.URL)
  @Size(min = 10, max = 480, message = ValidationMessage.SIZE)
  @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
  private String imageUrl2;

  @NoSpacesAtEnds(message = ValidationMessage.NO_SPACES_AT_ENDS)
  @Size(min = 5, max = 90, message = ValidationMessage.SIZE)
  @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
  private String imageAlt2;

  @URL(regexp = "^[^\\s]*$", message = ValidationMessage.URL)
  @Size(min = 10, max = 480, message = ValidationMessage.SIZE)
  @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
  private String imageUrl3;

  @NoSpacesAtEnds(message = ValidationMessage.NO_SPACES_AT_ENDS)
  @Size(min = 5, max = 90, message = ValidationMessage.SIZE)
  @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
  private String imageAlt3;

  @NoSpacesAtEnds(message = ValidationMessage.NO_SPACES_AT_ENDS)
  @Size(min = 5, max = 240, message = ValidationMessage.SIZE)
  @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
  private String detail;

  @NoSpacesAtEnds(message = ValidationMessage.NO_SPACES_AT_ENDS)
  @Size(min = 15, max = 680, message = ValidationMessage.SIZE)
  @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
  private String description;

  @NoSpacesAtEnds(message = ValidationMessage.NO_SPACES_AT_ENDS)
  @Size(min = 7, max = 190, message = ValidationMessage.SIZE)
  @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
  private String ingredients;

  @NoSpacesAtEnds(message = ValidationMessage.NO_SPACES_AT_ENDS)
  @Size(min = 3, max = 70, message = ValidationMessage.SIZE)
  @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
  private String category;

  @Digits(integer = 10, fraction = 2, message = ValidationMessage.DIGITS)
  @PositiveOrZero(message = ValidationMessage.POSITIVE_OR_ZERO)
  @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
  private BigDecimal price;

  @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
  private Boolean isHidden;
}
