package com.disoraya.sales_system.endpoint.sale.dto;

import java.math.BigDecimal;

public record OutSaleDetDto(
    Integer saleId,

    Integer productId,

    Integer quantitySold,

    BigDecimal unitPriceSold
) {
}
