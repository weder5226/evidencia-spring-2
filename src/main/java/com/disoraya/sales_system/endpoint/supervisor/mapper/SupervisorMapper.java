package com.disoraya.sales_system.endpoint.supervisor.mapper;

import com.disoraya.sales_system.endpoint.supervisor.dto.InpSupervisorDto;
import com.disoraya.sales_system.endpoint.supervisor.dto.OutSupervisorDto;
import com.disoraya.sales_system.endpoint.supervisor.model.Supervisor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class SupervisorMapper {
  @Autowired
  PasswordEncoder passwordEncoder;

  public abstract OutSupervisorDto entityToDto(Supervisor supervisor);

  @Mapping(target = "password", source = "password", qualifiedByName = "encodeString")
  @Mapping(target = "version", ignore = true)
  @Mapping(target = "id", ignore = true)
  public abstract Supervisor dtoToEntity(InpSupervisorDto dto);

  @Mapping(target = "password", source = "password", qualifiedByName = "encodeString")
  @Mapping(target = "version", ignore = true)
  @Mapping(target = "id", ignore = true)
  public abstract void entityFromDto(InpSupervisorDto dto, @MappingTarget Supervisor supervisor);

  @Named("encodeString")
  protected String encodeString(String password) {
    return password == null ? null : passwordEncoder.encode(password);
  }
}
