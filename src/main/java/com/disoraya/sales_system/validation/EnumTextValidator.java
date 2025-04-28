package com.disoraya.sales_system.validation;

import com.disoraya.sales_system.validation.annotation.EnumText;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumTextValidator implements ConstraintValidator<EnumText, String> {
  private Set<String> validValues;

  @Override
  public void initialize(EnumText annotation) {
    validValues = Stream.of(annotation.enumClass().getEnumConstants())
        .map(Enum::name)
        .collect(Collectors.toSet());
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null || validValues.contains(value)) return true;

    String mss = String.join(", ", validValues);
    context.disableDefaultConstraintViolation();

    context.buildConstraintViolationWithTemplate(
        String.format("%s. It is allowed: %s", context.getDefaultConstraintMessageTemplate(), mss)
    ).addConstraintViolation();

    return false;
  }
}
