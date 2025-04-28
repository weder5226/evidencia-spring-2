package com.disoraya.sales_system.endpoint.supervisor.dto;

import com.disoraya.sales_system.util.Role;
import com.disoraya.sales_system.validation.ValidationMessage;
import com.disoraya.sales_system.validation.annotation.EnumText;
import com.disoraya.sales_system.validation.group.OnCreate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record InpSupervisorDto(
    @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
    String name,

    @Email(message = ValidationMessage.EMAIL)
    @Size(min = 8, max = 70, message = ValidationMessage.SIZE)
    @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
    String email,

    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9\\s])", message = ValidationMessage.PATTERN_PWD)
    @Size(min = 6, max = 20, message = ValidationMessage.SIZE)
    @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
    String password,

    @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
    Boolean isEnabled,

    @EnumText(enumClass = Role.class, message = ValidationMessage.ENUM_TEXT)
    @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
    String role
) {
}
