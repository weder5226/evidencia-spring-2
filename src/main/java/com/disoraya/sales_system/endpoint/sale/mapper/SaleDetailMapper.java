package com.disoraya.sales_system.endpoint.sale.mapper;

import com.disoraya.sales_system.endpoint.product.model.Product;
import com.disoraya.sales_system.endpoint.sale.dto.InpSaleDetWithSaleIdDto;
import com.disoraya.sales_system.endpoint.sale.dto.OutSaleDetDto;
import com.disoraya.sales_system.endpoint.sale.model.Sale;
import com.disoraya.sales_system.endpoint.sale.model.SaleDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface SaleDetailMapper {
  @Mapping(target = "productId", source = "product.id")
  @Mapping(target = "saleId", source = "sale.id")
  OutSaleDetDto entityToDto(SaleDetail saleDetail);

  @Mapping(target = "product", source = "productRef")
  @Mapping(target = "sale", source = "saleRef")
  @Mapping(target = "version", ignore = true)
  @Mapping(target = "id", ignore = true)
  SaleDetail withSaleIdDtoToEntity(InpSaleDetWithSaleIdDto dto, Sale saleRef, Product productRef);
}
