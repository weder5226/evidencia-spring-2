package com.disoraya.sales_system.endpoint.sale.repository;

import com.disoraya.sales_system.endpoint.sale.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
  @Modifying
  @Query("DELETE FROM Sale s WHERE s.id = ?1")
  void deleteSearchingById(Integer id);
}