package com.tdd.catalog.annotation;

import com.tdd.catalog.utils.DateUtils;
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
@Constraint(validatedBy = CheckDateValidator.class)
public @interface ValidDate {
    String pattern() default DateUtils.DEFAULT_DATE_FORMAT;
    boolean allowBlank() default true;
    String message() default "Invalid date!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
