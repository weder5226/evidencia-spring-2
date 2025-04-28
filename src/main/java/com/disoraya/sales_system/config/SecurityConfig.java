package com.disoraya.sales_system.config;

import com.disoraya.sales_system.config.filter.JwtAuthenticationFilter;
import com.disoraya.sales_system.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Value("${security.cors.origin}")
  private String CORS_ORIGIN;

  @Autowired
  private JwtAuthenticationFilter authenticationFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
//        .securityMatcher("/api/**")
        .csrf(AbstractHttpConfigurer::disable)
        .cors(Customizer.withDefaults())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .authorizeHttpRequests(router -> router
            // Public Routes
            .requestMatchers(HttpMethod.POST, "/auth").permitAll()
            .requestMatchers("/error").permitAll()
            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

            // Protected routes for ADMIN and SALES
            .requestMatchers("/client/**").hasAnyRole(getRoles(Role.ADMIN, Role.SALES))
            .requestMatchers("/sale/**").hasAnyRole(getRoles(Role.ADMIN, Role.SALES))
            .requestMatchers("/sale-detail/**").hasAnyRole(getRoles(Role.ADMIN, Role.SALES))

            // Protected routes for ADMIN and INVENTORY
            .requestMatchers("/product/**").hasAnyRole(getRoles(Role.ADMIN, Role.INVENTORY))

            // Protected routes for ADMIN and CATALOG
            .requestMatchers("/catalog/**").hasAnyRole(getRoles(Role.ADMIN, Role.CATALOG))

            // Protected routes for only ADMIN
            .requestMatchers("/supervisor/**").hasRole(Role.ADMIN.name())

            // Any other route
            .requestMatchers("**").denyAll()
        );
    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(passwordEncoder());
    return new ProviderManager(provider);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  UrlBasedCorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.addAllowedOrigin(CORS_ORIGIN);
    configuration.addAllowedHeader(HttpHeaders.AUTHORIZATION);
    configuration.setAllowedMethods(getHttpMethods(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.DELETE));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  private String[] getRoles(Role... roles) {
    return Arrays.stream(roles)
        .map(Enum::name)
        .toArray(String[]::new);
  }

  private List<String> getHttpMethods(HttpMethod... methods) {
    return Arrays.stream(methods)
        .map(HttpMethod::name)
        .toList();
  }
}
