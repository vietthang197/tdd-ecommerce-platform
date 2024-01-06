package com.tdd.catalog.services;

import com.tdd.catalog.dto.BaseResponse;
import com.tdd.catalog.dto.ProductDto;
import com.tdd.catalog.vm.CreateProductVM;

public interface ProductService {
    BaseResponse<ProductDto> createProduct(CreateProductVM request);
}
