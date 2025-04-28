package com.disoraya.sales_system.endpoint.supervisor.repository.view;

import com.disoraya.sales_system.endpoint.supervisor.dto.projection.SupervisorPwdView;
import com.disoraya.sales_system.endpoint.supervisor.dto.projection.SupervisorSimpleView;
import com.disoraya.sales_system.endpoint.supervisor.model.Supervisor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface SupervisorViewRepository extends Repository<Supervisor, Integer> {
  Optional<SupervisorSimpleView> findById(Integer id);

  Optional<SupervisorPwdView> findWithPwdByEmail(String email);

  Slice<SupervisorSimpleView> findByIsEnabled(Boolean isEnabled, Pageable pageable);
}
