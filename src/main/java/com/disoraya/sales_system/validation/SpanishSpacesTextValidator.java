package com.disoraya.sales_system.validation;

import com.disoraya.sales_system.validation.annotation.SpanishSpacesText;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SpanishSpacesTextValidator implements ConstraintValidator<SpanishSpacesText, String> {
  private static final String REGEX_WITH_ACCENTS = "^[a-zA-ZáéíóúñüÁÉÍÓÚÑÜ\\s]+$";
  private static final String REGEX_WITHOUT_ACCENTS = "^[a-zA-ZñÑ\\s]+$";
  private boolean noAccent;

  @Override
  public void initialize(SpanishSpacesText annotation) {
    this.noAccent = annotation.noAccent();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null || value.isEmpty()) return true;
    String regex = noAccent ? REGEX_WITHOUT_ACCENTS : REGEX_WITH_ACCENTS;
    return value.matches(regex);
  }
}
