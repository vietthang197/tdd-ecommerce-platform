package com.tdd.catalog.validate;

import java.util.List;
import java.util.function.Supplier;

public final class ValidateProcess {

    public static ResultValidator execute(List<Supplier<ValidateStep>> validateStepList, IContextValidator contextValidator) {
        for (Supplier<ValidateStep> stepSupplier : validateStepList) {
            ValidateStep step = stepSupplier.get();
            ResultValidator resultValidator = step.process(contextValidator);
            if (!resultValidator.isOk())
                return resultValidator;
        }
        return ResultValidator.success();
    }
}
