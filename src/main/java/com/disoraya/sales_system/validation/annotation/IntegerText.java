package com.disoraya.sales_system.validation.annotation;

import com.disoraya.sales_system.validation.IntegerTextValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IntegerTextValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface IntegerText {
  String message() default "{validation.integerText}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
