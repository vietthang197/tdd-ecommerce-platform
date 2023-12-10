package com.tdd.core.annotation;

import com.tdd.core.utils.DateUtils;
import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({ FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = CheckDateValidator.class)
public @interface ValidDate {
    String pattern() default DateUtils.DEFAULT_DATE_FORMAT;
    boolean allowBlank() default true;
    String message() default "Invalid date!";
}
