package com.disoraya.sales_system.endpoint.client.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "first_name", nullable = false, length = 100)
  private String firstName;

  @Column(name = "last_name", nullable = false, length = 100)
  private String lastName;

  // @NaturalId
  @Column(name = "id_number", nullable = false, unique = true, length = 20)
  private String idNumber;

  @Column(name = "phone_number", nullable = false, length = 30)
  private String phoneNumber;

  @Column(name = "city", length = 50)
  private String city;

  @Column(name = "address", length = 80)
  private String address;

  @Column(name = "email", length = 80)
  private String email;

  @Version
  @Column(name = "version", nullable = false)
  private short version;

  @Override
  public final boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null)
      return false;
    Class<?> oEffectiveClass = o instanceof HibernateProxy
        ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
        : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy
        ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
        : this.getClass();
    if (thisEffectiveClass != oEffectiveClass)
      return false;
    Client client = (Client) o;
    return getId() != null && Objects.equals(getId(), client.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy
        ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
        : getClass().hashCode();
  }
}