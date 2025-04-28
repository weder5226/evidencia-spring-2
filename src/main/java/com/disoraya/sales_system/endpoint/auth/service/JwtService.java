package com.disoraya.sales_system.endpoint.auth.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
  String generateToken(UserDetails user);

  Integer extractSubject(String jwt);
}
