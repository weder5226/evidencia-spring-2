package com.disoraya.sales_system.endpoint.client.dto.projection;

import com.blazebit.persistence.view.EntityView;
import com.disoraya.sales_system.endpoint.client.model.Client;

@EntityView(Client.class)
public interface ClientView extends ClientSimpleView {
  String getCity();

  String getAddress();

  String getEmail();
}