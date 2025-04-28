package com.disoraya.sales_system.validation;

import com.disoraya.sales_system.validation.annotation.NoSpacesAtEnds;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NoSpacesAtEndsValidator implements ConstraintValidator<NoSpacesAtEnds, String> {
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null || value.isEmpty()) return true;
    return value.equals(value.trim());
  }
}
