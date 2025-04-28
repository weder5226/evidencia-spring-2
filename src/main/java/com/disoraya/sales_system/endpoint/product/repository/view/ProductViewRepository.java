package com.disoraya.sales_system.endpoint.product.repository.view;

import com.disoraya.sales_system.endpoint.product.dto.projection.ProductView;
import com.disoraya.sales_system.endpoint.product.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ProductViewRepository extends Repository<Product, Integer> {
  Optional<ProductView> findById(Integer id);

  Slice<ProductView> findByQuantityStock(Integer quantityStock, Pageable pageable);

  Slice<ProductView> findByQuantityStockNot(Integer quantityStock, Pageable pageable);
}