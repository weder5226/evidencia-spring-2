package com.disoraya.sales_system.endpoint.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

public record OutProductDetDto(
    Integer id,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String name,

    String imageUrl,

    String imageAlt,

    String imageUrl2,

    String imageAlt2,

    String imageUrl3,

    String imageAlt3,

    String detail,

    String description,

    String ingredients,

    String category,

    BigDecimal price
) {
}
