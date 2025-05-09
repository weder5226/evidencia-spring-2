package com.disoraya.sales_system.validation.annotation;

import com.disoraya.sales_system.validation.NotBlankIfPresentValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NotBlankIfPresentValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotBlankIfPresent {
  String message() default "{validation.notBlankIfPresent}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
