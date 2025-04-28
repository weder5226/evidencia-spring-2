package com.disoraya.sales_system.validation.annotation;

import com.disoraya.sales_system.validation.NoSpacesAtEndsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NoSpacesAtEndsValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface NoSpacesAtEnds {
  String message() default "{validation.noSpacesAtEnds}";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
