package com.tdd.catalog.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({ FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = CheckJsonValidator.class)
public @interface ValidJson {
    boolean allowBlank() default true;
    boolean checkHashMapJson() default true;
    String message() default "Invalid Json Data!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
