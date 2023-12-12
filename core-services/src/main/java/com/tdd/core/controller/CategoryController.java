package com.tdd.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tdd.core.dto.BaseResponse;
import com.tdd.core.dto.CategoryDto;
import com.tdd.core.services.CategoryService;
import com.tdd.core.vm.CreateCategoryVM;
import com.tdd.core.vm.UpdateGeneralCategoryVM;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public BaseResponse<CategoryDto> createCategory(@RequestBody @Valid CreateCategoryVM request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws JsonProcessingException {
        return categoryService.createCategory(request);
    }

    @PutMapping
    public BaseResponse<CategoryDto> updateCategory(@RequestBody @Valid UpdateGeneralCategoryVM request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws JsonProcessingException {
        return categoryService.updateGeneralCategory(request);
    }
}
