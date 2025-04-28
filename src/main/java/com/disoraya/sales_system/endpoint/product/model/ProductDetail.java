package com.disoraya.sales_system.endpoint.product.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "product_details")
public class ProductDetail {
  @Id
  @Column(name = "product_id", nullable = false)
  private Integer id;

  @Column(name = "image_url", nullable = false, length = 500)
  private String imageUrl;

  @Column(name = "image_alt", nullable = false, length = 100)
  private String imageAlt;

  @Column(name = "image_url_2", nullable = false, length = 500)
  private String imageUrl2;

  @Column(name = "image_alt_2", nullable = false, length = 100)
  private String imageAlt2;

  @Column(name = "image_url_3", nullable = false, length = 500)
  private String imageUrl3;

  @Column(name = "image_alt_3", nullable = false, length = 100)
  private String imageAlt3;

  @Column(name = "detail", nullable = false, length = 250)
  private String detail;

  @Column(name = "description", nullable = false, length = 700)
  private String description;

  @Column(name = "ingredients", nullable = false, length = 200)
  private String ingredients;

  @Column(name = "category", nullable = false, length = 80)
  private String category;

  @Column(name = "price", nullable = false, precision = 10, scale = 2)
  private BigDecimal price;

  @Column(name = "is_hidden", nullable = false)
  private Boolean isHidden;

  @OneToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", insertable = false, updatable = false)
  @MapsId
  private Product product;

  @PrePersist
  public void prePersist() {
    if (isHidden == null) isHidden = true;
  }

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    ProductDetail that = (ProductDetail) o;
    return getId() != null && Objects.equals(getId(), that.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }
}