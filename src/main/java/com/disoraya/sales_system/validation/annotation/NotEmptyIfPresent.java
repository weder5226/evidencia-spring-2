package com.disoraya.sales_system.validation.annotation;

import com.disoraya.sales_system.validation.NotEmptyIfPresentValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NotEmptyIfPresentValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyIfPresent {
  String message() default "{validation.notEmptyIfPresent}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
