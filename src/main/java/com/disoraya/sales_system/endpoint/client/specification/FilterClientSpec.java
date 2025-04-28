package com.disoraya.sales_system.endpoint.client.specification;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.spring.data.repository.BlazeSpecification;
import com.disoraya.sales_system.endpoint.client.dto.param.FilterClientDto;
import com.disoraya.sales_system.endpoint.client.model.Client_;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;

@AllArgsConstructor
public class FilterClientSpec implements BlazeSpecification {
  private FilterClientDto dto;

  @Override
  public void applySpecification(String s, CriteriaBuilder<?> builder) {
    if (StringUtils.hasText(dto.getFirstNamePrefix())) {
      builder.where(Client_.FIRST_NAME).like(false).value(dto.getFirstNamePrefix().trim() + "%").noEscape();
    }

    if (StringUtils.hasText(dto.getLastNamePrefix())) {
      builder.where(Client_.LAST_NAME).like(false).value(dto.getLastNamePrefix().trim() + "%").noEscape();
    }

    if (StringUtils.hasText(dto.getIdNumberPrefix())) {
      builder.where(Client_.ID_NUMBER).like().value(dto.getIdNumberPrefix().trim() + "%").noEscape();
    }
  }
}
