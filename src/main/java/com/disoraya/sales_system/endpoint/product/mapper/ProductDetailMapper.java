package com.disoraya.sales_system.endpoint.product.mapper;

import com.disoraya.sales_system.endpoint.product.dto.InpProductDetDto;
import com.disoraya.sales_system.endpoint.product.dto.InpProductDetWithProdIdDto;
import com.disoraya.sales_system.endpoint.product.dto.OutProductDetDto;
import com.disoraya.sales_system.endpoint.product.model.Product;
import com.disoraya.sales_system.endpoint.product.model.ProductDetail;
import org.mapstruct.*;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProductDetailMapper {
  @Mapping(target = "name", source = "productName")
  OutProductDetDto entityToDto(ProductDetail productDetail, String productName);

  @Mapping(target = "product", source = "productRef")
  @Mapping(target = "id", ignore = true)
  ProductDetail withProdIdDtoToEntity(InpProductDetWithProdIdDto dto, Product productRef);

  @Mapping(target = "product", ignore = true)
  @Mapping(target = "id", ignore = true)
  void entityFromDto(InpProductDetDto dto, @MappingTarget ProductDetail productDetail);
}
