package com.disoraya.sales_system.endpoint.auth.service;

import com.disoraya.sales_system.endpoint.auth.dto.InpAuthDto;
import com.disoraya.sales_system.endpoint.auth.dto.OutAuthDto;

public interface AuthService {
  OutAuthDto createToken(InpAuthDto authDto);
}
