package com.disoraya.sales_system.endpoint.client.repository.view;

import com.disoraya.sales_system.endpoint.client.dto.projection.ClientSimpleView;
import com.disoraya.sales_system.endpoint.client.dto.projection.ClientView;
import com.disoraya.sales_system.endpoint.client.model.Client;
import com.disoraya.sales_system.endpoint.client.specification.FilterClientSpec;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ClientViewRepository extends Repository<Client, Integer> {
  Optional<ClientView> findById(Integer id);

  Slice<ClientSimpleView> findBy(FilterClientSpec specification, Pageable pageable);
}