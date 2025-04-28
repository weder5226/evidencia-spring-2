package com.disoraya.sales_system.endpoint.client.dto;

import com.disoraya.sales_system.validation.ValidationMessage;
import com.disoraya.sales_system.validation.annotation.IntegerText;
import com.disoraya.sales_system.validation.annotation.NoSpacesAtEnds;
import com.disoraya.sales_system.validation.annotation.SpanishSpacesText;
import com.disoraya.sales_system.validation.group.OnCreate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record InpClientDto(
    @SpanishSpacesText(noAccent = true, message = ValidationMessage.SPANISH_SPACES_TEXT)
    @NoSpacesAtEnds(message = ValidationMessage.NO_SPACES_AT_ENDS)
    @Size(min = 3, max = 90, message = ValidationMessage.SIZE)
    @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
    String firstName,

    @SpanishSpacesText(noAccent = true, message = ValidationMessage.SPANISH_SPACES_TEXT)
    @NoSpacesAtEnds(message = ValidationMessage.NO_SPACES_AT_ENDS)
    @Size(min = 3, max = 90, message = ValidationMessage.SIZE)
    @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
    String lastName,

    @IntegerText(message = ValidationMessage.INTEGER_TEXT)
    @Size(min = 6, max = 18, message = ValidationMessage.SIZE)
    @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
    String idNumber,

    @IntegerText(message = ValidationMessage.INTEGER_TEXT)
    @Size(min = 5, max = 28, message = ValidationMessage.SIZE)
    @NotNull(message = ValidationMessage.NOT_NULL, groups = OnCreate.class)
    String phoneNumber,

    @NoSpacesAtEnds(message = ValidationMessage.NO_SPACES_AT_ENDS)
    @Size(min = 3, max = 45, message = ValidationMessage.SIZE)
    String city,

    @NoSpacesAtEnds(message = ValidationMessage.NO_SPACES_AT_ENDS)
    @Size(min = 5, max = 75, message = ValidationMessage.SIZE)
    String address,

    @Email(message = ValidationMessage.EMAIL)
    @Size(min = 8, max = 70, message = ValidationMessage.SIZE)
    String email
) {
}