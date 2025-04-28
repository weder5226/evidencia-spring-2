package com.disoraya.sales_system.endpoint.product.repository.view;

import com.disoraya.sales_system.endpoint.product.dto.projection.ProductDetailSimpleView;
import com.disoraya.sales_system.endpoint.product.dto.projection.ProductDetailView;
import com.disoraya.sales_system.endpoint.product.model.ProductDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ProductDetailViewRepository extends Repository<ProductDetail, Integer> {
  Optional<ProductDetailView> findById(Integer id);

  Slice<ProductDetailSimpleView> findByIsHidden(Boolean isHidden, Pageable pageable);

  List<ProductDetailView> findAllByIsHidden(Boolean isHidden);
}