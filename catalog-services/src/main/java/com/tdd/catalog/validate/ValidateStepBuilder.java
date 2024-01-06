package com.tdd.catalog.validate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ValidateStepBuilder {
    private final List<Supplier<ValidateStep>> validateProcessList;

    private ValidateStepBuilder() {
        this.validateProcessList = new ArrayList<>();
    }

    public List<Supplier<ValidateStep>> then(Supplier<ValidateStep> validateProcessSupplier) {
        this.validateProcessList.add(validateProcessSupplier);
        return this.validateProcessList;
    }

    public static ValidateStepBuilder builder() {
        return new ValidateStepBuilder();
    }
}
