package com.disoraya.sales_system.endpoint.sale.model;

import com.disoraya.sales_system.endpoint.product.model.Product;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sale_details")
public class SaleDetail {
  @Builder.Default
  @EmbeddedId
  private SaleDetailId id = new SaleDetailId();

  @Column(name = "quantity_sold", nullable = false)
  private Integer quantitySold;

  @Column(name = "unit_price_sold", nullable = false, precision = 10, scale = 2)
  private BigDecimal unitPriceSold;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "sale_id", insertable = false, updatable = false)
  @MapsId(SaleDetailId_.SALE_ID)
  private Sale sale;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "product_id", insertable = false, updatable = false)
  @MapsId(SaleDetailId_.PRODUCT_ID)
  private Product product;

  @Version
  @Column(name = "version", nullable = false)
  private short version;

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    SaleDetail that = (SaleDetail) o;
    return getId() != null && Objects.equals(getId(), that.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }
}