package com.disoraya.sales_system.endpoint.client.dto;

public record OutClientDto(
    Integer id,

    String firstName,

    String lastName,

    String idNumber,

    String phoneNumber,

    String city,

    String address,

    String email
) {
}
