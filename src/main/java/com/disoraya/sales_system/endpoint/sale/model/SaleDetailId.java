package com.disoraya.sales_system.endpoint.sale.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SaleDetailId implements Serializable {
  @Column(name = "sale_id")
  private Integer saleId;

  @Column(name = "product_id")
  private Integer productId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SaleDetailId that = (SaleDetailId) o;
    return getSaleId() != null && Objects.equals(saleId, that.saleId) && getProductId() != null && Objects.equals(productId, that.productId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(saleId, productId);
  }
}