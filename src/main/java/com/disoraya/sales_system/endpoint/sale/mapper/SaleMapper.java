package com.disoraya.sales_system.endpoint.sale.mapper;

import com.disoraya.sales_system.endpoint.client.model.Client;
import com.disoraya.sales_system.endpoint.sale.dto.InpSaleDto;
import com.disoraya.sales_system.endpoint.sale.dto.InpSaleWithDetailsDto;
import com.disoraya.sales_system.endpoint.sale.dto.OutSaleDto;
import com.disoraya.sales_system.endpoint.sale.model.Sale;
import org.mapstruct.*;

import java.math.BigDecimal;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface SaleMapper {
  @Mapping(target = "total", source = "totalSum")
  @Mapping(target = "clientIdNumber", source = "idNumber")
  OutSaleDto entityToDto(Sale sale, String idNumber, BigDecimal totalSum);

  @Mapping(target = "client", source = "clientRef")
  @Mapping(target = "details", ignore = true)
  @Mapping(target = "version", ignore = true)
  @Mapping(target = "id", ignore = true)
  Sale withDetailsDtoToEntity(InpSaleWithDetailsDto dto, Client clientRef);

  @Mapping(target = "client", source = "clientRef")
  @Mapping(target = "details", ignore = true)
  @Mapping(target = "version", ignore = true)
  @Mapping(target = "id", ignore = true)
  void entityFromDto(InpSaleDto dto, Client clientRef, @MappingTarget Sale sale);
}
