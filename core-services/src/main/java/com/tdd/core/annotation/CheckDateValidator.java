package com.tdd.core.annotation;

import com.tdd.core.utils.DateUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckDateValidator implements ConstraintValidator<ValidDate, String> {

    private String pattern;
    private boolean allowBlank;

    @Override
    public void initialize(ValidDate constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
        this.allowBlank = constraintAnnotation.allowBlank();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return DateUtils.isValid(value, pattern, allowBlank);
    }
}
