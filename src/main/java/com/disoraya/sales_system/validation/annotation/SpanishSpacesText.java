package com.disoraya.sales_system.validation.annotation;

import com.disoraya.sales_system.validation.SpanishSpacesTextValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SpanishSpacesTextValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface SpanishSpacesText {
  String message() default "{validation.spanishText}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  boolean noAccent() default false;
}
