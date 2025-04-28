package com.disoraya.sales_system.validation;

import com.disoraya.sales_system.validation.annotation.IntegerText;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IntegerTextValidator implements ConstraintValidator<IntegerText, String> {
  private static final String REGEX = "^[0-9]+$";

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null || value.isEmpty()) return true;
    return value.matches(REGEX);
  }
}
