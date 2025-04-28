package com.disoraya.sales_system.endpoint.supervisor.dto.projection;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.disoraya.sales_system.endpoint.supervisor.model.Supervisor;
import com.disoraya.sales_system.util.Role;

@EntityView(Supervisor.class)
public interface SupervisorSimpleView {
  @IdMapping
  Integer getId();

  String getName();

  String getEmail();

  Role getRole();

  Boolean getIsEnabled();
}