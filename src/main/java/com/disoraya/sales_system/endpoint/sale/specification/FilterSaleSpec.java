package com.disoraya.sales_system.endpoint.sale.specification;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.spring.data.repository.BlazeSpecification;
import com.disoraya.sales_system.endpoint.client.model.Client_;
import com.disoraya.sales_system.endpoint.sale.dto.param.FilterSaleDto;
import com.disoraya.sales_system.endpoint.sale.model.Sale_;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;

@AllArgsConstructor
public class FilterSaleSpec implements BlazeSpecification {
  private FilterSaleDto dto;

  @Override
  public void applySpecification(String s, CriteriaBuilder<?> builder) {
    if (StringUtils.hasText(dto.getIdNumberPrefix())) {
      builder.where(Sale_.CLIENT + "." + Client_.ID_NUMBER)
          .like().value(dto.getIdNumberPrefix().trim() + "%").noEscape();
    }

    if (dto.getMinDate() != null) {
      builder.where(Sale_.SALE_DATE).ge(dto.getMinDate());
    }

    if (dto.getMaxDate() != null) {
      builder.where(Sale_.SALE_DATE).le(dto.getMaxDate());
    }

    if (dto.getIsDelivered() != null) {
      if (dto.getIsDelivered()) {
        builder.where(Sale_.DELIVERY_DATE).isNotNull();
      } else {
        builder.where(Sale_.DELIVERY_DATE).isNull();
      }
    }
  }
}
