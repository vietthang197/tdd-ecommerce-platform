package com.tdd.catalog.validate;

import com.tdd.catalog.vm.BaseRequest;

import java.util.Map;

public interface IContextValidator {
    Map<String, Object> getContext();
    BaseRequest getRequest();
}
