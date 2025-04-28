package com.disoraya.sales_system.endpoint.auth.dto;

public record OutAuthDto(
    String token,
    
    String authType
) {
}
