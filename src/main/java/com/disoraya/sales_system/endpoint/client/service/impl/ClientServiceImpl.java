package com.disoraya.sales_system.endpoint.client.service.impl;

import com.disoraya.sales_system.endpoint.client.dto.InpClientDto;
import com.disoraya.sales_system.endpoint.client.dto.OutClientDto;
import com.disoraya.sales_system.endpoint.client.dto.param.FilterClientDto;
import com.disoraya.sales_system.endpoint.client.dto.projection.ClientSimpleView;
import com.disoraya.sales_system.endpoint.client.dto.projection.ClientView;
import com.disoraya.sales_system.endpoint.client.mapper.ClientMapper;
import com.disoraya.sales_system.endpoint.client.model.Client;
import com.disoraya.sales_system.endpoint.client.model.Client_;
import com.disoraya.sales_system.endpoint.client.repository.ClientRepository;
import com.disoraya.sales_system.endpoint.client.repository.view.ClientViewRepository;
import com.disoraya.sales_system.endpoint.client.service.ClientService;
import com.disoraya.sales_system.endpoint.client.specification.FilterClientSpec;
import com.disoraya.sales_system.exception.DuplicateException;
import com.disoraya.sales_system.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientServiceImpl implements ClientService {
  private final ClientRepository clientRepo;
  private final ClientViewRepository clientViewRepo;
  private final ClientMapper mapper;

  @Override
  public String getNameById(Integer id) {
    return clientRepo.findNameById(id).orElseThrow(
        () -> new NotFoundException("Client with the given ID does not exist")
    );
  }

  @Override
  public ClientView getById(Integer id) {
    return clientViewRepo.findById(id).orElseThrow(
        () -> new NotFoundException("Client with the given ID does not exist")
    );
  }

  @Override
  public Slice<ClientSimpleView> getAllFiltered(FilterClientDto filterClientDto, Integer page, Integer size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(
        Sort.Order.asc(Client_.FIRST_NAME),
        Sort.Order.asc(Client_.LAST_NAME),
        Sort.Order.asc(Client_.ID_NUMBER),
        Sort.Order.asc(Client_.ID)
    ));
    return clientViewRepo.findBy(new FilterClientSpec(filterClientDto), pageable);
  }

  @Override
  @Transactional
  public OutClientDto create(InpClientDto clientDto) {
    if (clientRepo.existsByIdNumber(clientDto.idNumber())) {
      throw new DuplicateException("ID number is already in use by another client");
    }

    Client client = clientRepo.save(mapper.dtoToEntity(clientDto));
    return mapper.entityToDto(client);
  }

  @Override
  @Transactional
  public OutClientDto updateById(Integer id, InpClientDto clientDto) {
    Client client = clientRepo.findById(id).orElseThrow(
        () -> new NotFoundException("Client with the given ID does not exist")
    );
    String newIdNum = clientDto.idNumber();
    if (newIdNum != null
        && !newIdNum.equals(client.getIdNumber())
        && clientRepo.existsByIdNumber(newIdNum)
    ) {
      throw new DuplicateException("New ID number is already in use by another client");
    }
    mapper.entityFromDto(clientDto, client);

    return mapper.entityToDto(client);
  }

  @Override
  @Transactional
  public void deleteById(Integer id) {
    if (!clientRepo.existsById(id)) {
      throw new NotFoundException("Client with the given ID does not exist");
    }
    clientRepo.deleteSearchingById(id);
  }
}
