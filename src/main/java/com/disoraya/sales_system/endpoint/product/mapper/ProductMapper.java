package com.disoraya.sales_system.endpoint.product.mapper;

import com.disoraya.sales_system.endpoint.product.dto.InpProductDto;
import com.disoraya.sales_system.endpoint.product.dto.OutProductDto;
import com.disoraya.sales_system.endpoint.product.model.Product;
import org.mapstruct.*;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProductMapper {
  OutProductDto entityToDto(Product product);

  @Mapping(target = "version", ignore = true)
  @Mapping(target = "id", ignore = true)
  Product dtoToEntity(InpProductDto dto);

  @Mapping(target = "version", ignore = true)
  @Mapping(target = "id", ignore = true)
  void entityFromDto(InpProductDto dto, @MappingTarget Product product);
}
