package com.disoraya.sales_system.endpoint.supervisor.repository;

import com.disoraya.sales_system.endpoint.supervisor.model.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SupervisorRepository extends JpaRepository<Supervisor, Integer> {
  boolean existsByEmail(String email);

  @Modifying
  @Query("DELETE FROM Supervisor s WHERE s.id = ?1")
  void deleteSearchingById(Integer id);
}