package com.disoraya.sales_system.endpoint.client.mapper;

import com.disoraya.sales_system.endpoint.client.dto.InpClientDto;
import com.disoraya.sales_system.endpoint.client.dto.OutClientDto;
import com.disoraya.sales_system.endpoint.client.model.Client;
import org.mapstruct.*;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ClientMapper {
  OutClientDto entityToDto(Client client);

  @Mapping(target = "version", ignore = true)
  @Mapping(target = "id", ignore = true)
  Client dtoToEntity(InpClientDto dto);

  @Mapping(target = "version", ignore = true)
  @Mapping(target = "id", ignore = true)
  void entityFromDto(InpClientDto dto, @MappingTarget Client client);
}
