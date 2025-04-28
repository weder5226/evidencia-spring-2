package com.disoraya.sales_system.endpoint.product.dto;

public record OutProductDto(
    Integer id,

    String name,

    Integer quantityStock
) {
}
