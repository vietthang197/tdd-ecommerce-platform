package com.tdd.core.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tdd.core.dto.BaseResponse;
import com.tdd.core.dto.CategoryDto;
import com.tdd.core.vm.CreateCategoryVM;
import com.tdd.core.vm.UpdateGeneralCategoryVM;

public interface CategoryService {
    BaseResponse<CategoryDto> createCategory(CreateCategoryVM request) throws JsonProcessingException;

    BaseResponse<CategoryDto> updateGeneralCategory(UpdateGeneralCategoryVM request);
}
