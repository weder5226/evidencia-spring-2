package com.disoraya.sales_system.endpoint.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetService extends UserDetailsService {
  UserDetails loadUserById(Integer id);
}
