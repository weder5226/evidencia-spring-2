package com.disoraya.sales_system.endpoint.sale.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OutSaleDto(
    Integer id,

    LocalDate saleDate,

    LocalDate deliveryDate,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String clientIdNumber,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    BigDecimal total
) {
}
