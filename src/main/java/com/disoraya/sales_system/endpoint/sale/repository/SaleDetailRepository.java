package com.disoraya.sales_system.endpoint.sale.repository;

import com.disoraya.sales_system.endpoint.sale.model.SaleDetail;
import com.disoraya.sales_system.endpoint.sale.model.SaleDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SaleDetailRepository extends JpaRepository<SaleDetail, SaleDetailId> {
  @Modifying
  @Query("DELETE FROM SaleDetail sd WHERE sd.id.saleId = :saleId")
  void deleteSearchingBySaleId(Integer saleId);

  @Modifying
  @Query("DELETE FROM SaleDetail sd WHERE sd.id = :id")
  void deleteSearchingById(SaleDetailId id);
}