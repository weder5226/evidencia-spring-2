package com.disoraya.sales_system.endpoint.supervisor.service;

import com.disoraya.sales_system.endpoint.supervisor.dto.InpSupervisorDto;
import com.disoraya.sales_system.endpoint.supervisor.dto.OutSupervisorDto;
import com.disoraya.sales_system.endpoint.supervisor.dto.projection.SupervisorSimpleView;
import org.springframework.data.domain.Slice;

public interface SupervisorService {
  Slice<SupervisorSimpleView> getAllByIsEnabled(Boolean isEnabled, Integer page, Integer size);

  OutSupervisorDto create(InpSupervisorDto supervisorDto);

  OutSupervisorDto updateById(Integer id, InpSupervisorDto supervisorDto);

  void deleteById(Integer id);
}
