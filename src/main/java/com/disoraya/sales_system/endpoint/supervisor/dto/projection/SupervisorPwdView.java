package com.disoraya.sales_system.endpoint.supervisor.dto.projection;

import com.blazebit.persistence.view.EntityView;
import com.disoraya.sales_system.endpoint.supervisor.model.Supervisor;

@EntityView(Supervisor.class)
public interface SupervisorPwdView extends SupervisorSimpleView {
  String getPassword();
}
