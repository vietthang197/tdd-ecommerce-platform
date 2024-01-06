package com.tdd.catalog.services.impl;

import com.tdd.catalog.constant.ProductTypeEnum;
import com.tdd.catalog.dto.BaseResponse;
import com.tdd.catalog.dto.ProductDto;
import com.tdd.catalog.exeception.InvalidDataRequestException;
import com.tdd.catalog.services.ProductService;
import com.tdd.catalog.utils.ResponseCode;
import com.tdd.catalog.validate.ContextValidator;
import com.tdd.catalog.validate.IContextValidator;
import com.tdd.catalog.validate.ResultValidator;
import com.tdd.catalog.validate.ValidateProcess;
import com.tdd.catalog.validate.ValidateStep;
import com.tdd.catalog.validate.ValidateStepBuilder;
import com.tdd.catalog.vm.CreateProductVM;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public BaseResponse<ProductDto> createProduct(CreateProductVM request) {

        validateCreateProduct(request);

        return null;
    }

    private void validateCreateProduct(CreateProductVM request) {
        final ContextValidator contextValidator = new ContextValidator(request);
        List<Supplier<ValidateStep>> validateStepBuilder = makeValidateStepForCreateProduct(contextValidator);
        ResultValidator resultValidator = ValidateProcess.execute(validateStepBuilder, contextValidator);
        if (resultValidator.isOk()) {
            // xử lý tiếp
        } else {
            throw new InvalidDataRequestException(resultValidator.getErrMessage());
        }
    }

    private List<Supplier<ValidateStep>> makeValidateStepForCreateProduct(IContextValidator contextValidator) {
        CreateProductVM request = (CreateProductVM) contextValidator.getRequest();
        ProductTypeEnum createProductType = ProductTypeEnum.valueOf(request.getProductType());
        switch (createProductType) {
            case GROUPED -> {
                return makeValidateStepForGroupedProduct();
            }
            case VARIANT -> {
                return makeValidateStepForVariantProduct();
            }
            case EXTERNAL -> {
                return makeValidateStepForExternalProduct();
            }
            default -> {
                return makeValidateStepForSimpleProduct();
            }
        }
    }

    private List<Supplier<ValidateStep>> makeValidateStepForExternalProduct() {
        return Collections.emptyList();
    }

    private List<Supplier<ValidateStep>> makeValidateStepForVariantProduct() {
        return Collections.emptyList();
    }

    private List<Supplier<ValidateStep>> makeValidateStepForGroupedProduct() {
        return Collections.emptyList();
    }

    private List<Supplier<ValidateStep>> makeValidateStepForSimpleProduct() {
        return Collections.emptyList();
    }

    private ValidateStep requiredDefaultPrice() {
        return (context) -> {
            CreateProductVM request = (CreateProductVM) context.getRequest();
            if (Objects.isNull(request.getDefaultPrice()))
                return new ResultValidator(ResponseCode.GW400, "Default Price không được trống", false);
            else
                return ResultValidator.success();
        };
    }
}
