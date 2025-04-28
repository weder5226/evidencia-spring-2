package com.disoraya.sales_system.endpoint.sale.model;

import com.disoraya.sales_system.endpoint.client.model.Client;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "sales")
public class Sale {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "sale_date", nullable = false)
  private LocalDate saleDate;

  @Column(name = "delivery_date")
  private LocalDate deliveryDate;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;

  @OneToMany(mappedBy = SaleDetail_.SALE, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
  private List<SaleDetail> details = new ArrayList<>();

  @Version
  @Column(name = "version", nullable = false)
  private short version;

  @PrePersist
  public void prePersist() {
    if (saleDate == null) saleDate = LocalDate.now();
  }

  public void addDetail(SaleDetail detail) {
    details.add(detail);
    detail.setSale(this);
  }

  public boolean removeDetail(SaleDetail detail) {
    boolean isRemoved = details.remove(detail);
    if (isRemoved) detail.setSale(null);
    return isRemoved;
  }

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    Sale sale = (Sale) o;
    return getId() != null && Objects.equals(getId(), sale.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }
}