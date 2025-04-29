package com.disoraya.sales_system.endpoint.product.repository;

import com.disoraya.sales_system.endpoint.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
  boolean existsByName(String name);

  boolean existsByNameIgnoreCase(String name);

  @Query("SELECT p.name FROM Product p WHERE p.id = ?1")
  Optional<String> findNameById(Integer id);

  List<Product> findByIdIn(Collection<Integer> ids);

  @Modifying
  @Query("DELETE FROM Product p WHERE p.id = ?1")
  void deleteSearchingById(Integer id);
}