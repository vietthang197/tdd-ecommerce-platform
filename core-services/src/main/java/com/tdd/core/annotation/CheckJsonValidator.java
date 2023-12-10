package com.tdd.core.annotation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import java.util.Map;

public class CheckJsonValidator implements ConstraintValidator<ValidJson, String> {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger logger = LogManager.getLogger();


    private boolean allowBlank;
    private boolean checkHashMapJson;

    @Override
    public void initialize(ValidJson constraintAnnotation) {
        this.allowBlank = constraintAnnotation.allowBlank();
        this.checkHashMapJson = constraintAnnotation.checkHashMapJson();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Strings.isBlank(value))
            return allowBlank;
        try {
            if (checkHashMapJson)
                MAPPER.readValue(value, new TypeReference<Map<String, String>>() {});
            else
                MAPPER.readTree(value);

            return true;
        } catch (Exception e) {
            logger.error(e);
        }
        return false;
    }
}
