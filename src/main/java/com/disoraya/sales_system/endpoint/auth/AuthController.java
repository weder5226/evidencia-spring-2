package com.disoraya.sales_system.endpoint.auth;

import com.disoraya.sales_system.endpoint.auth.dto.InpAuthDto;
import com.disoraya.sales_system.endpoint.auth.dto.OutAuthDto;
import com.disoraya.sales_system.endpoint.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication", description = "Controller responsible for handling authentication")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
  private final AuthService authService;

  @SecurityRequirements()
  @Operation(summary = "Get token By Credentials", description = "Endpoint to obtain an authentication token using user credentials")
  @PostMapping
  @ResponseStatus(code = HttpStatus.OK)
  public OutAuthDto createToken(
      @Valid @RequestBody InpAuthDto authDto
  ) {
    return authService.createToken(authDto);
  }
}
