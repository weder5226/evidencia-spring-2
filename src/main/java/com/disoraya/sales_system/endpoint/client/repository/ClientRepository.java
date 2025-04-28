package com.disoraya.sales_system.endpoint.client.repository;

import com.disoraya.sales_system.endpoint.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
  boolean existsByIdNumber(String idNumber);

  @Query("SELECT c.idNumber FROM Client c WHERE c.id = :id")
  Optional<String> findIdNumberById(Integer id);

  @Query("SELECT CONCAT(c.firstName, ' ', c.lastName) FROM Client c WHERE c.id = :id")
  Optional<String> findNameById(Integer id);

  @Modifying
  @Query("DELETE FROM Client c WHERE c.id = :id")
  void deleteSearchingById(Integer id);
}
