package com.disoraya.sales_system.validation;

import com.disoraya.sales_system.validation.annotation.NotBlankIfPresent;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotBlankIfPresentValidator implements ConstraintValidator<NotBlankIfPresent, String> {
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) return true;
    return !value.isBlank();
  }
}
