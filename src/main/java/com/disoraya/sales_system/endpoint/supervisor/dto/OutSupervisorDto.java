package com.disoraya.sales_system.endpoint.supervisor.dto;

import com.disoraya.sales_system.util.Role;

public record OutSupervisorDto(
    Integer id,

    String name,

    String email,

    Role role
) {
}
