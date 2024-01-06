package com.tdd.catalog.validate;

import com.tdd.catalog.vm.BaseRequest;

import java.util.HashMap;
import java.util.Map;

public class ContextValidator implements IContextValidator {
    private final Map<String, Object> context;
    private final BaseRequest request;

    public ContextValidator(BaseRequest request) {
        this.context = new HashMap<>();
        this.request = request;
    }

    @Override
    public Map<String, Object> getContext() {
        return context;
    }

    @Override
    public BaseRequest getRequest() {
        return request;
    }
}
