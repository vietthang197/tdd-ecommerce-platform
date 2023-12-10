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
@Constraint(validatedBy = CheckJsonValidator.class)
public @interface ValidJson {
    boolean allowBlank() default true;
    boolean checkHashMapJson() default true;
    String message() default "Invalid Json Data!";
}
