package com.disoraya.sales_system.validation;

import com.disoraya.sales_system.validation.annotation.NotEmptyIfPresent;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotEmptyIfPresentValidator implements ConstraintValidator<NotEmptyIfPresent, String> {
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) return true;
    return !value.isEmpty();
  }
}
