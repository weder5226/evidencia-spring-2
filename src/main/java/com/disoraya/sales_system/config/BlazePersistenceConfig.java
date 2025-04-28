package com.disoraya.sales_system.config;

import com.blazebit.persistence.Criteria;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.integration.view.spring.EnableEntityViews;
import com.blazebit.persistence.spi.CriteriaBuilderConfiguration;
import com.blazebit.persistence.spring.data.repository.config.EnableBlazeRepositories;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.spi.EntityViewConfiguration;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableEntityViews(basePackages = "com.disoraya.sales_system.endpoint.*.dto.projection")
@EnableJpaRepositories(basePackages = "com.disoraya.sales_system.endpoint",
    includeFilters = @ComponentScan.Filter(
        type = FilterType.ASPECTJ,
        pattern = "com.disoraya.sales_system.endpoint.*.repository.*"
    )
)
@EnableBlazeRepositories(basePackages = "com.disoraya.sales_system.endpoint",
    includeFilters = @ComponentScan.Filter(
        type = FilterType.ASPECTJ,
        pattern = "com.disoraya.sales_system.endpoint.*.repository.view.*"
    )
)
public class BlazePersistenceConfig {
  @PersistenceUnit
  private EntityManagerFactory entityManagerFactory;

  @Bean
  @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
  @Lazy(false)
  public CriteriaBuilderFactory createCriteriaBuilderFactory() {
    CriteriaBuilderConfiguration config = Criteria.getDefault();
    return config.createCriteriaBuilderFactory(entityManagerFactory);
  }

  @Bean
  @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
  @Lazy(false)
  public EntityViewManager createEntityViewManager(CriteriaBuilderFactory cbf, EntityViewConfiguration evc) {
    return evc.createEntityViewManager(cbf);
  }
}
