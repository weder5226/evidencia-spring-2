package com.disoraya.sales_system.endpoint.product.dto;

import com.disoraya.sales_system.validation.ValidationMessage;
import com.disoraya.sales_system.validation.annotation.NoSpacesAtEnds;
import com.disoraya.sales_system.validation.annotation.SpanishSpacesText;
import com.disoraya.sales_system.validation.group.OnCreate;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record InpProductDto(
    @SpanishSpacesText(message = ValidationMessage.SPANISH_SPACES_TEXT)
    @NoSpacesAtEnds(message = ValidationMessage.NO_SPACES_AT_ENDS)
    @Size(min = 3, max = 45, message = ValidationMessage.SIZE)
    @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
    String name,

    @Max(value = 5000, message = ValidationMessage.MAX)
    @PositiveOrZero(message = ValidationMessage.POSITIVE_OR_ZERO)
    @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
    Integer quantityStock
) {
}
