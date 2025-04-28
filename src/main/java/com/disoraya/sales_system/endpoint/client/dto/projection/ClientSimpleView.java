package com.disoraya.sales_system.endpoint.client.dto.projection;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.disoraya.sales_system.endpoint.client.model.Client;

@EntityView(Client.class)
public interface ClientSimpleView {
  @IdMapping
  Integer getId();

  String getFirstName();

  String getLastName();

  String getIdNumber();

  String getPhoneNumber();
}