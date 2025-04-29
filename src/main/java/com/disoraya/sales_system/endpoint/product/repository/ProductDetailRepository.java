package com.disoraya.sales_system.endpoint.product.repository;

import com.disoraya.sales_system.endpoint.product.model.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
  @Modifying
  @Query("DELETE FROM ProductDetail pd WHERE pd.id = ?1")
  void deleteSearchingById(Integer id);
}