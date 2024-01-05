package com.tdd.catalog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tdd.catalog.dto.BaseResponse;
import com.tdd.catalog.dto.CategoryDto;
import com.tdd.catalog.dto.PagingDto;
import com.tdd.catalog.services.CategoryService;
import com.tdd.catalog.vm.CreateCategoryVM;
import com.tdd.catalog.vm.UpdateGeneralCategoryVM;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@SecurityRequirement(name = "Authorization")
@Tag(name = "CategoryController - Api liên quan tới catalog category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public BaseResponse<CategoryDto> createCategory(@RequestBody @Valid CreateCategoryVM request) throws JsonProcessingException {
        return categoryService.createCategory(request);
    }

    @PutMapping
    public BaseResponse<CategoryDto> updateCategory(@RequestBody @Valid UpdateGeneralCategoryVM request) throws JsonProcessingException {
        return categoryService.updateGeneralCategory(request);
    }

    @GetMapping
    public BaseResponse<PagingDto<CategoryDto>> findAllCategory(@RequestParam @Valid @NotNull Integer page, @RequestParam @Valid @NotNull Integer size) {
        return categoryService.findAll(page, size);
    }

    @GetMapping("/{categoryUrl}")
    public BaseResponse<CategoryDto> findByUrl(@PathVariable @Valid @NotNull String categoryUrl) {
        return categoryService.findByUrl(categoryUrl);
    }
}
