package com.tdd.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tdd.core.dto.BaseResponse;
import com.tdd.core.dto.CategoryDto;
import com.tdd.core.dto.PagingDto;
import com.tdd.core.services.CategoryService;
import com.tdd.core.vm.CreateCategoryVM;
import com.tdd.core.vm.UpdateGeneralCategoryVM;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
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
    public BaseResponse<PagingDto<CategoryDto>> findAllCategory(@RequestParam @NotNull Integer page, @RequestParam @NotNull Integer size) {
        return categoryService.findAll(page, size);
    }
}
