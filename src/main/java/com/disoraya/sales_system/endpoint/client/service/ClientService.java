package com.disoraya.sales_system.endpoint.client.service;

import com.disoraya.sales_system.endpoint.client.dto.InpClientDto;
import com.disoraya.sales_system.endpoint.client.dto.OutClientDto;
import com.disoraya.sales_system.endpoint.client.dto.param.FilterClientDto;
import com.disoraya.sales_system.endpoint.client.dto.projection.ClientSimpleView;
import com.disoraya.sales_system.endpoint.client.dto.projection.ClientView;
import org.springframework.data.domain.Slice;

public interface ClientService {
  String getNameById(Integer id);

  ClientView getById(Integer id);

  Slice<ClientSimpleView> getAllFiltered(FilterClientDto filterClientDto, Integer page, Integer size);

  OutClientDto create(InpClientDto clientDto);

  OutClientDto updateById(Integer id, InpClientDto clientDto);

  void deleteById(Integer idNumber);
}
