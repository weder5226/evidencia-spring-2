package com.disoraya.sales_system.endpoint.auth.dto;

import com.disoraya.sales_system.validation.ValidationMessage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record InpAuthDto(
    @Email(message = ValidationMessage.EMAIL)
    @Size(min = 8, max = 70, message = ValidationMessage.SIZE)
    @NotNull(message = ValidationMessage.NOT_NULL)
    String email,

    @Size(min = 3, max = 30, message = ValidationMessage.SIZE)
    @NotNull(message = ValidationMessage.NOT_NULL)
    String password
) {
}
