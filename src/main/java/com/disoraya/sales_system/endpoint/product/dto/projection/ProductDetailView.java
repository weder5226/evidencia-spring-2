package com.disoraya.sales_system.endpoint.product.dto.projection;

import com.blazebit.persistence.view.EntityView;
import com.disoraya.sales_system.endpoint.product.model.ProductDetail;

@EntityView(ProductDetail.class)
public interface ProductDetailView extends ProductDetailSimpleView {
  String getImageUrl();

  String getImageAlt();

  String getImageUrl2();

  String getImageAlt2();

  String getImageUrl3();

  String getImageAlt3();

  String getDescription();

  String getIngredients();

  String getCategory();
}