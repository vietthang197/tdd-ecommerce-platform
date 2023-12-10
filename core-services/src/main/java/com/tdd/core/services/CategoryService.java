package com.tdd.core.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tdd.core.dto.BaseResponse;
import com.tdd.core.dto.CategoryDto;
import com.tdd.core.vm.CreateCategoryVM;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    BaseResponse<CategoryDto> createCategory(CreateCategoryVM request) throws JsonProcessingException;
}
