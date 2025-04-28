package com.disoraya.sales_system.endpoint.auth.service.impl;

import com.disoraya.sales_system.endpoint.auth.dto.InpAuthDto;
import com.disoraya.sales_system.endpoint.auth.dto.OutAuthDto;
import com.disoraya.sales_system.endpoint.auth.service.AuthService;
import com.disoraya.sales_system.endpoint.auth.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {
  private final AuthenticationManager authManager;
  private final JwtService jwtService;

  @Override
  public OutAuthDto createToken(InpAuthDto authDto) {
    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
        authDto.email(), authDto.password()
    );
    Authentication authentication = authManager.authenticate(auth);
    auth.eraseCredentials();

    User user = (User) authentication.getPrincipal();
    String jwt = jwtService.generateToken(user);
    return new OutAuthDto(jwt, "Bearer Token");
  }
}
