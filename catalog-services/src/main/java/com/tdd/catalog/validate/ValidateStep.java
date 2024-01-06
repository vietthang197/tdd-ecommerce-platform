package com.tdd.catalog.validate;

@FunctionalInterface
public interface ValidateStep {
    ResultValidator process(IContextValidator context);
}
