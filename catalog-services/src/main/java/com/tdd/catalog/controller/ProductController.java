package com.tdd.catalog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tdd.catalog.dto.BaseResponse;
import com.tdd.catalog.dto.CategoryDto;
import com.tdd.catalog.dto.ProductDto;
import com.tdd.catalog.services.ProductService;
import com.tdd.catalog.vm.CreateCategoryVM;
import com.tdd.catalog.vm.CreateProductVM;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@SecurityRequirement(name = "Authorization")
@Tag(name = "ProductController - Api liên quan tới catalog product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public BaseResponse<ProductDto> createProduct(@RequestBody @Valid CreateProductVM request) throws JsonProcessingException {
        return productService.createProduct(request);
    }
}
